package com.smartsense.test.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.smartsense.test.R
import com.smartsense.test.database.entity.User
import com.smartsense.test.database.entity.UserDB
import com.smartsense.test.ui.adapters.MainAdapter

import com.smartsense.test.ui.adapters.MainAdapterWIthPageList
import com.smartsense.test.utils.Utils
import com.smartsense.test.viewModels.MainViewModelWithPaging
import kotlinx.android.synthetic.main.commno_toolbar.*
import kotlinx.android.synthetic.main.content_default.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val userViewModel by viewModel<MainViewModelWithPaging>()
    var userist = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
setupUi()


    }
fun setupUi(){
    toolbarTitle.text = getString(R.string.app_name)
    icBack.setOnClickListener(this)
}
    override fun onBackPressed() {
        finish()
    }

    override fun onResume() {
        super.onResume()
        getUserData()
        userViewModel.loadRecipesPersistence()
    }

    private fun getUserData() {
        if (!Utils.isNetworkAvailable(this)) {
            val mainAdapter = MainAdapter(arrayListOf())
            rvList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            rvList.adapter = mainAdapter
            userViewModel.offline.observe(this,
                Observer<List<UserDB>> {
                    it?.let {
                        if (it.isNotEmpty()) {
                            mainAdapter.replaceData(it)
                        }
                    }
                })
        } else {
            val adapter = MainAdapterWIthPageList(this)

            userViewModel.recipes.observe(this, Observer {
                adapter.submitList(it)
            })
            rvList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            rvList.adapter = adapter
        }


    }

    override fun onClick(v: View?) {
      when(v?.id){
        R.id.icBack->finish()
      }
    }

}