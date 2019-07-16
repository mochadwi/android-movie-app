package io.mochadwi.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

import lombok.Data;

@Data
@Generated("com.robohorse.robopojogenerator")
public class BaseEntity<T> {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<T> results;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;
}