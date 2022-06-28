package com.project.read_pro.Fragment;

import static com.project.read_pro.utils.AppConstant.PRODUCT;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.read_pro.R;
import com.project.read_pro.adapter.SaveProductAdapter;
import com.project.read_pro.databinding.FragmentSaveBinding;
import com.project.read_pro.model.Product;
import com.project.read_pro.model.SaveProduct;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.utils.SaveProductUtils;
import com.project.read_pro.view.ProductDetailActivity;
import com.project.read_pro.view_model.SaveProductViewModel;

import java.util.List;

public class SaveFragment extends Fragment {
    private FragmentSaveBinding binding;
    private List<SaveProduct> saveProducts;
    private RecyclerView saveProductRecyclerView;
    private SaveProductAdapter saveProductAdapter;
    private TextView tvCountSaveProduct;
    private SaveProductViewModel saveProductViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSaveBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        displayCountSave();
        displaySaveProduct();

    }

    private void init() {
        saveProductViewModel = new ViewModelProvider(getActivity()).get(SaveProductViewModel.class);
        saveProducts = SaveProductUtils.getInstance().getSaveProducts();
        tvCountSaveProduct = binding.tvCountSaveProduct;
        saveProductRecyclerView = binding.saveProductRecyclerView;
    }
    private void displayCountSave(){
        tvCountSaveProduct.setText(SaveProductUtils.getInstance().countSaveProduct() + " items");
    }
    private void displaySaveProduct(){
        saveProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        saveProductRecyclerView.setHasFixedSize(true);
        saveProductAdapter = new SaveProductAdapter(getActivity(), saveProducts);
        saveProductRecyclerView.setAdapter(saveProductAdapter);
        saveProductAdapter.setOnClickSaveProductRemove(new SaveProductAdapter.SaveProductRemoveOnClickHandler() {
            @Override
            public void onClick(SaveProduct saveProduct, int position) {
                String token = "Bearer " + LoginUtils.getInstance(getActivity()).getUserToken();
                int saveProductId = saveProduct.getId();
                saveProductViewModel.deleteSaveProduct(token, saveProductId).observe(getActivity(), responseBody -> {
                    SaveProductUtils.getInstance().removeSaveProduct(saveProductId);
                    saveProductAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Save Product Removed.", Toast.LENGTH_SHORT).show();
                });
            }
        });
        saveProductAdapter.setOnClickSaveProduct(new SaveProductAdapter.SaveProductOnClickHandler() {
            @Override
            public void onClick(SaveProduct saveProduct, int position) {
                Product product = saveProduct.getProduct();
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra(PRODUCT, product);
                startActivity(intent);
            }
        });
    }


}