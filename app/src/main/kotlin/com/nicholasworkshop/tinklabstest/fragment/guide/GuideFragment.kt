package com.nicholasworkshop.tinklabstest.fragment.guide

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicholasworkshop.tinklabstest.MainApplication
import javax.inject.Inject

private const val ARG_GUIDE_TYPE = "ARG_GUIDE_TYPE"

fun newGuideFragmentInstance(@GuideType guideType: Int): GuideFragment {
    val fragment = GuideFragment()
    val args = Bundle()
    args.putInt(ARG_GUIDE_TYPE, guideType)
    fragment.arguments = args
    return fragment
}

class GuideFragment : Fragment() {

    @Inject
    internal lateinit var guidePresenter: GuidePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.activity!!.application as MainApplication).component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return guidePresenter.createView(inflater, container!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        @GuideType val guideType = arguments!!.getInt(ARG_GUIDE_TYPE)
        guidePresenter.viewCreated(guideType)
    }

}