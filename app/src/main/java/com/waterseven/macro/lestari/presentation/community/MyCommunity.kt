package com.waterseven.macro.lestari.presentation.community

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.waterseven.macro.lestari.data.community.CommunityData
import com.waterseven.macro.lestari.data.culture.CulturesData
import com.waterseven.macro.lestari.presentation.community.adapter.RvCommunityAdapter
import com.waterseven.macro.lestari.databinding.FragmentMyCommunityBinding
import com.waterseven.macro.lestari.model.community.Community
import com.waterseven.macro.lestari.presentation.home.adapter.CultureAdapter
import com.waterseven.macro.lestari.presentation.home.culture.CultureFragmentDirections

class MyCommunity : Fragment() {
    private lateinit var binding: FragmentMyCommunityBinding
    private lateinit var communityAdapter: RvCommunityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyCommunityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }



    private fun setUpView() {
        val communityData = CommunityData.dummyCommunity

        communityAdapter = RvCommunityAdapter { community ->
            val data = CommunityFragmentDirections.actionCommunityFragmentToMyCommunity2(communityData.get(0))
            findNavController().navigate(data)
        }

        binding?.rvMyCommunity?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = communityAdapter
        }

        val communityHasJoin : MutableList<Community> = mutableListOf()

        //menambahkan data ke komunitas saya
       communityData.forEach{
           if(it.join == true){
                communityHasJoin.add(it)
           }
       }
        communityAdapter.submitList(communityHasJoin)

    }


}