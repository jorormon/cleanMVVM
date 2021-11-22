package com.jordiortuno.cleanmvvm.framework.ui.main.library
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jordiortuno.cleanmvvm.R
import com.jordiortuno.cleanmvvm.domain.Document

class DocumentsAdapter(
    private val documents: MutableList<Document> = mutableListOf(),

    private val itemClickListener: (Document) -> Unit
) : RecyclerView.Adapter<DocumentsAdapter.ViewHolder>() {

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val previewImageView: ImageView = view.findViewById(R.id.ivPreview)
    val titleTextView: TextView = view.findViewById(R.id.tvTitle)
    val sizeTextView: TextView = view.findViewById(R.id.tvSize)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_document, parent, false)
    )
  }

  override fun getItemCount() = documents.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
    /*glide.load(documents[position].thumbnail)
        .error(glide.load(R.drawable.preview_missing))
        .into(holder.previewImageView)*/
    holder.previewImageView.setImageResource(R.drawable.preview_missing)
    holder.titleTextView.text = documents[position].name
    holder.sizeTextView.text = "1150"//StringUtil.readableFileSize(documents[position].size)
    holder.itemView.setOnClickListener { itemClickListener.invoke(documents[position]) }
  }

  internal fun update(newDocuments: List<Document>) {
    documents.clear()
    documents.addAll(newDocuments)

    notifyDataSetChanged()
  }
}