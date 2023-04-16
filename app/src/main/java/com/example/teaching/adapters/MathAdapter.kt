package com.example.teaching.adapters

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.teaching.R
import com.example.teaching.databinding.ItemTestBinding
import com.example.teaching.models.MathTest

class MathAdapter(var testList: List<MathTest>, var onClick: OnClickTestListener) :
    RecyclerView.Adapter<MathAdapter.MyViewHolder>() {
    val answersList: HashMap<Int, String> = HashMap()

    inner class MyViewHolder(var binding: ItemTestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.apply {
                tvQuestionNumber.text = "${testList[adapterPosition].num} - savol"
                if (testList[adapterPosition].ques.isNotEmpty()) {
                    tvQuestion.visibility = View.VISIBLE
                    tvQuestion.text = testList[adapterPosition].ques
                    imageView.visibility = View.GONE
                } else {
                    tvQuestion.visibility = View.GONE
                    imageView.visibility = View.VISIBLE
                    val base64QuesImg: String =
                        testList[adapterPosition].qImg.split(",").get(index = 1)

                    val imageAsBytesQuesImg: ByteArray =
                        Base64.decode(base64QuesImg.toByteArray(), Base64.DEFAULT)
                    imageView.setImageBitmap(
                        BitmapFactory.decodeByteArray(
                            imageAsBytesQuesImg,
                            0,
                            imageAsBytesQuesImg.size
                        )
                    )

                }

                if (testList[adapterPosition].v1.isNotEmpty()) {
                    radio1.visibility = View.VISIBLE
                    radio2.visibility = View.VISIBLE
                    radio3.visibility = View.VISIBLE
                    radio4.visibility = View.VISIBLE

                    text1.text = testList[adapterPosition].v1
                    text2.text = testList[adapterPosition].v2
                    text3.text = testList[adapterPosition].v3
                    text4.text = testList[adapterPosition].v4
                    image1.visibility = View.GONE
                    image2.visibility = View.GONE
                    image3.visibility = View.GONE
                    image4.visibility = View.GONE
                } else {
                    text1.visibility = View.GONE
                    text2.visibility = View.GONE
                    text3.visibility = View.GONE
                    text4.visibility = View.GONE

                    image1.visibility = View.VISIBLE
                    image2.visibility = View.VISIBLE
                    image3.visibility = View.VISIBLE
                    image4.visibility = View.VISIBLE
                    imageEncodeDecode(adapterPosition, image1, image2, image3, image4)
                }

                linearRadio1.setOnClickListener {
                    if (testList[adapterPosition].v1.isNotEmpty()) {
                        answersList[adapterPosition] = testList[adapterPosition].v1
                    } else {
                        answersList[adapterPosition] = testList[adapterPosition].img1
                    }
                    radio1.setImageResource(R.drawable.ic_check)
                    radio2.setImageResource(R.drawable.ic_no_check)
                    radio3.setImageResource(R.drawable.ic_no_check)
                    radio4.setImageResource(R.drawable.ic_no_check)
                }

                linearRadio2.setOnClickListener {
                    if (testList[adapterPosition].v2.isNotEmpty()) {
                        answersList[adapterPosition] = testList[adapterPosition].v2
                    } else {
                        answersList[adapterPosition] = testList[adapterPosition].img2
                    }
                    radio1.setImageResource(R.drawable.ic_no_check)
                    radio2.setImageResource(R.drawable.ic_check)
                    radio3.setImageResource(R.drawable.ic_no_check)
                    radio4.setImageResource(R.drawable.ic_no_check)
                }
                linearRadio3.setOnClickListener {
                    if (testList[adapterPosition].v3.isNotEmpty()) {
                        answersList[adapterPosition] = testList[adapterPosition].v3
                    } else {
                        answersList[adapterPosition] = testList[adapterPosition].img3
                    }
                    radio1.setImageResource(R.drawable.ic_no_check)
                    radio2.setImageResource(R.drawable.ic_no_check)
                    radio3.setImageResource(R.drawable.ic_check)
                    radio4.setImageResource(R.drawable.ic_no_check)
                }
                linearRadio4.setOnClickListener {
                    if (testList[adapterPosition].v4.isNotEmpty()) {
                        answersList[adapterPosition] = testList[adapterPosition].v4
                    } else {
                        answersList[adapterPosition] = testList[adapterPosition].img4
                    }
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

    private fun imageEncodeDecode(
        adapterPosition: Int,
        img1: ImageView,
        img2: ImageView,
        img3: ImageView,
        img4: ImageView,
    ) {

        val base64Img1: String =
            testList[adapterPosition].img1.split(",").get(index = 1)
        val base64Img2: String =
            testList[adapterPosition].img2.split(",").get(index = 1)
        val base64Img3: String =
            testList[adapterPosition].img3.split(",").get(index = 1)
        val base64Img4: String =
            testList[adapterPosition].img4.split(",").get(index = 1)

        val imageAsBytesImg1: ByteArray =
            Base64.decode(base64Img1.toByteArray(), Base64.DEFAULT)
        img1.setImageBitmap(
            BitmapFactory.decodeByteArray(
                imageAsBytesImg1,
                0,
                imageAsBytesImg1.size
            )
        )
        val imageAsBytesImg2: ByteArray =
            Base64.decode(base64Img2.toByteArray(), Base64.DEFAULT)
        img2.setImageBitmap(
            BitmapFactory.decodeByteArray(
                imageAsBytesImg2,
                0,
                imageAsBytesImg2.size
            )
        )
        val imageAsBytesImg3: ByteArray =
            Base64.decode(base64Img3.toByteArray(), Base64.DEFAULT)
        img3.setImageBitmap(
            BitmapFactory.decodeByteArray(
                imageAsBytesImg3,
                0,
                imageAsBytesImg3.size
            )
        )
        val imageAsBytesImg4: ByteArray =
            Base64.decode(base64Img4.toByteArray(), Base64.DEFAULT)
        img4.setImageBitmap(
            BitmapFactory.decodeByteArray(
                imageAsBytesImg4,
                0,
                imageAsBytesImg4.size
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
//        val animation: Animation =
//            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recyclearview_animation)
//        holder.itemView.startAnimation(animation)
        holder.itemView.setOnClickListener {
            onClick.onClickTest(position)
        }
    }

    override fun getItemCount() = testList.size


    interface OnClickTestListener {
        fun onClickTest(position: Int)
    }
}