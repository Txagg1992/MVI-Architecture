package com.curiousapps.mvi_architecture.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.curiousapps.mvi_architecture.R
import com.curiousapps.mvi_architecture.ui.main.state.MainStateEvent
import com.curiousapps.mvi_architecture.ui.main.state.MainStateEvent.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid Activity")
    }

    fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer{dataState ->
            println("*** Debug: DataState: $dataState ***")
            dataState.blogPosts?.let {blogPosts ->
                //set BlogPost Data
                viewModel.setBlogListData(blogPosts)
            }
            dataState.user?.let {user ->
                // set User Data
                viewModel.setUser(user)
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            viewState.blogPosts?.let {
                println("###Debug: Setting blog posts to recyclerView: $it")
            }

            viewState.user?.let {
                println("### Debug: Setting user data: $it")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_get_user -> triggerGetUserEvent()

            R.id.action_get_blog -> triggerGetBlogEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetBlogEvent() {
        viewModel.setStateEvent(GetBlogPostsEvent())
    }

    private fun triggerGetUserEvent() {
        viewModel.setStateEvent(GetUserEvent("1"))
    }
}
