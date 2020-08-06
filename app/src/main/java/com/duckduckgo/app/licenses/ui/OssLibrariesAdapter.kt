/*
 * Copyright (c) 2020 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.app.licenses.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duckduckgo.app.browser.R
import com.duckduckgo.app.licenses.model.OssLibrary
import kotlinx.android.synthetic.main.item_oss_license.view.oss_license
import kotlinx.android.synthetic.main.item_oss_license.view.oss_link
import kotlinx.android.synthetic.main.item_oss_license.view.oss_name

class OssLibrariesAdapter(
    private val onItemClick: (OssLibrary) -> Unit,
    private val onLicenseLink: (OssLibrary) -> Unit
) : RecyclerView.Adapter<OssLibrariesAdapter.LibraryViewHolder>() {

    private var licensesViewData: MutableList<OssLibrary> = mutableListOf()

    class LibraryViewHolder(
        val root: View,
        val name: TextView,
        val license: TextView,
        val link: TextView
    ) : RecyclerView.ViewHolder(root)

    override fun getItemCount() = licensesViewData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_oss_license, parent, false)
        return LibraryViewHolder(root, root.oss_name, root.oss_license, root.oss_link)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val viewElement = licensesViewData[position]
        holder.itemView.setOnClickListener { onItemClick.invoke(viewElement) }
        holder.name.text = viewElement.name
        holder.license.text = viewElement.license
        holder.license.setOnClickListener { onLicenseLink.invoke(viewElement) }
        holder.link.text = viewElement.link
    }

    fun notifyChanges(newList: List<OssLibrary>) {
        licensesViewData = newList.toMutableList()
        notifyDataSetChanged()
    }
}