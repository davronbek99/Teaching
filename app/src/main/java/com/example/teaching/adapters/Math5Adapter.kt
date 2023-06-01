package com.example.teaching.adapters

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.teaching.R
import com.example.teaching.databinding.ItemTest5Binding
import com.example.teaching.databinding.ItemTestBinding
import com.example.teaching.models.Math5Test
import com.example.teaching.models.MathTest

class Math5Adapter(var testList: List<Math5Test>, var onClick: OnClickTestListener) :
    RecyclerView.Adapter<Math5Adapter.MyViewHolder>() {
    val answersList: HashMap<Int, String> = HashMap()

    inner class MyViewHolder(var binding: ItemTest5Binding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.apply {
                tvQuestionNumber.text = "${testList[adapterPosition].num} - savol"
                tvQuestion.text = testList[adapterPosition].ques

                if (testList[adapterPosition].v1.isNotEmpty()) {
                    radio1.visibility = View.VISIBLE
                    radio2.visibility = View.VISIBLE
                    radio3.visibility = View.VISIBLE
                    radio4.visibility = View.VISIBLE

                    text1.text = testList[adapterPosition].v1
                    text2.text = testList[adapterPosition].v2
                    text3.text = testList[adapterPosition].v3
                    text4.text = testList[adapterPosition].v4
                } else {
                    text1.visibility = View.GONE
                    text2.visibility = View.GONE
                    text3.visibility = View.GONE
                    text4.visibility = View.GONE
                }

                linearRadio1.setOnClickListener {
                    answersList[adapterPosition] = testList[adapterPosition].v1

                    radio1.setImageResource(R.drawable.ic_check)
                    radio2.setImageResource(R.drawable.ic_no_check)
                    radio3.setImageResource(R.drawable.ic_no_check)
                    radio4.setImageResource(R.drawable.ic_no_check)
                }

                linearRadio2.setOnClickListener {
                    answersList[adapterPosition] = testList[adapterPosition].v2

                    radio1.setImageResource(R.drawable.ic_no_check)
                    radio2.setImageResource(R.drawable.ic_check)
                    radio3.setImageResource(R.drawable.ic_no_check)
                    radio4.setImageResource(R.drawable.ic_no_check)
                }
                linearRadio3.setOnClickListener {
                    answersList[adapterPosition] = testList[adapterPosition].v3

                    radio1.setImageResource(R.drawable.ic_no_check)
                    radio2.setImageResource(R.drawable.ic_no_check)
                    radio3.setImageResource(R.drawable.ic_check)
                    radio4.setImageResource(R.drawable.ic_no_check)
                }
                linearRadio4.setOnClickListener {
                    answersList[adapterPosition] = testList[adapterPosition].v4

                    radio1.setImageResource(R.drawable.ic_no_check)
                    radio2.setImageResource(R.drawable.ic_no_check)
                    radio3.setImageResource(R.drawable.ic_no_check)
                    radio4.setImageResource(R.drawable.ic_check)
                }


            }
            val animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.rv_item_anim)
            binding.root.startAnimation(animation)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTest5Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
        val animation: Animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recyclearview_animation)
        holder.itemView.startAnimation(animation)
        holder.itemView.setOnClickListener {
            onClick.onClickTest(position)
        }
    }

    override fun getItemCount() = testList.size


    interface OnClickTestListener {
        fun onClickTest(position: Int)
    }
}