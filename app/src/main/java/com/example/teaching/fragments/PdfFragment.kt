package com.example.teaching.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teaching.R
import com.example.teaching.databinding.FragmentPdfBinding
import com.github.barteksc.pdfviewer.PDFView

class PdfFragment : Fragment() {

    private lateinit var binding: FragmentPdfBinding
    private lateinit var valueStr: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        valueStr = arguments?.getString("class", "").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPdfBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pdfView: PDFView = binding.pdfView

        when (valueStr) {
            "math 5" -> {
                pdfView.fromAsset("matematika_5_uzb.pdf").load()
            }
            "math 6" -> {
                pdfView.fromAsset("matematika_6_uzb.pdf").load()
            }
            "math 7" -> {
                pdfView.fromAsset("algebra_7_uzb.pdf").load()
            }
            "geo 7" -> {
                pdfView.fromAsset("geometriya_7_uzb.pdf").load()
            }
            "math 8" -> {
                pdfView.fromAsset("algebra_8_uzb.pdf").load()
            }
            "geo 8" -> {
                pdfView.fromAsset("geometriya_8_uzb.pdf").load()
            }
            "math 9" -> {
                pdfView.fromAsset("algebra_9_uzb.pdf").load()
            }
            "geo 9" -> {
                pdfView.fromAsset("geometriya_9_uzb.pdf").load()
            }

        }
    }
}