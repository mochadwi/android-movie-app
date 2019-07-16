package io.mochadwi.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.mochadwi.presentation.R;
import io.mochadwi.presentation.model.MovieModel;

/**
 * Adaptar that manages a collection of {@link MovieModel}.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final LayoutInflater layoutInflater;

    private List<MovieModel> moviesCollection;

    private OnItemClickListener onItemClickListener;

    @Inject
    MoviesAdapter(Context context) {
        this.layoutInflater =
            (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.moviesCollection = Collections.emptyList();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        final MovieModel movieModel = this.moviesCollection.get(position);
        holder.textViewTitle.setText(movieModel.getOriginalTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MoviesAdapter.this.onItemClickListener != null) {
                    MoviesAdapter.this.onItemClickListener.onMovieItemClicked(movieModel);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (this.moviesCollection != null) ? this.moviesCollection.size() : 0;
    }

    public void setMoviesCollection(Collection<MovieModel> moviesCollection) {
        this.validateMoviesCollection(moviesCollection);
        this.moviesCollection = (List<MovieModel>) moviesCollection;
        this.notifyDataSetChanged();
    }

    private void validateMoviesCollection(Collection<MovieModel> moviesCollection) {
        if (moviesCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onMovieItemClicked(MovieModel movieModel);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView textViewTitle;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
