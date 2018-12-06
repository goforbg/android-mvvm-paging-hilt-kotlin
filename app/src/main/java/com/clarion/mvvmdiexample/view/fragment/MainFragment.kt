package com.clarion.mvvmdiexample.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.clarion.mvvmdiexample.BR
import com.clarion.mvvmdiexample.R
import com.clarion.mvvmdiexample.databinding.FragmentMainBinding
import com.clarion.mvvmdiexample.view.activity.PagerActivity
import com.clarion.mvvmdiexample.view.activity.PersonListActivity
import com.clarion.mvvmdiexample.view.navigator.MainFrgNavigator
import com.clarion.mvvmdiexample.viewmodel.activity.MainViewModel
import com.clarion.mvvmdiexample.viewmodel.fragment.MainFrgViewModel
import javax.inject.Inject


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MainFragment : BaseFragment<FragmentMainBinding,MainFrgViewModel>(),MainFrgNavigator {

    private var apiKey: String? = null
    private var actViewModel:MainViewModel? = null

    @Inject
    protected lateinit var mViewModel: MainFrgViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            apiKey = it.getString(ARG_PARAM1)
        actViewModel = it.getSerializable(ARG_PARAM2) as MainViewModel? }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        mViewModel.setNavigator(this)
        super.onActivityCreated(savedInstanceState)
    }

    override fun getViewModel() = mViewModel

    override fun getBindingVariable() = BR.mainFrgViewModel

    override fun getLayoutId() = R.layout.fragment_main

    override fun openDBActivity() {
        startActivity(Intent(context, PersonListActivity::class.java))
    }

    override fun openViewPagerActivity() {
        startActivity(Intent(context, PagerActivity::class.java))
    }

    override fun openRemoteDataFragment() {
        actViewModel?.setFrgTitle(getString(R.string.title_remote_data_list_fragment))

        val ft = fragmentManager?.beginTransaction()
        var fragment = fragmentManager?.findFragmentByTag(RemoteDataFragment.TAG)

        if(null == fragment)
            fragment = RemoteDataFragment.newInstance(apiKey!!)

        ft!!.replace(R.id.frg_container, fragment,
                RemoteDataFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun openGalleryViewFragment() {
        actViewModel?.setFrgTitle(getString(R.string.title_gallery_fragment))

        val ft = fragmentManager?.beginTransaction()
        var fragment = fragmentManager?.findFragmentByTag(GalleryFragment.TAG)

        if(null == fragment)
            fragment = GalleryFragment.newInstance()

        ft!!.replace(R.id.frg_container, fragment,
                GalleryFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    companion object {

        val TAG = MainFragment::class.java.simpleName

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @param apiKey Parameter 1.
         * @return A new instance of fragment MainFragment.
         */
        @JvmStatic
        fun newInstance(apiKey: String,mainActViewModel:MainViewModel) =
                MainFragment()
                        .apply{ arguments = Bundle()
                                .apply{
                                    putString(ARG_PARAM1,apiKey)
                                    putSerializable(ARG_PARAM2,mainActViewModel)} }
    }
}
