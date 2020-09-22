package ash.sasamkar.plobaltask.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ash.sasamkar.plobaltask.R
import ash.sasamkar.plobaltask.adapter.StoreAdapter
import ash.sasamkar.plobaltask.model.ApiResponse
import ash.sasamkar.plobaltask.model.Store
import ash.sasamkar.plobaltask.rest.StoreApi
import kotlinx.android.synthetic.main.store_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreFragment : Fragment() {

    companion object {
        fun newInstance() = StoreFragment()
    }

    private lateinit var viewModel: StoreViewModel
    private lateinit var storeList : List<Store>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)//menu creation
        return inflater.inflate(R.layout.store_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StoreViewModel::class.java)
        try{
            StoreApi().getStore().enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                    val store = response.body()?.getResults()//Gson().toJson(response.body()?.getResults())
                    println("Response $store")
                    store?.let {
                        storeList = it
                        showStores(it,0)
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    println("Api Fail : " + t.message)
                }

            })

        }
        catch (e: Exception){
            println("Api Exception : $e")

        }


    }

    private fun sortList(stores: Array<Store>,flag: Int ){

        for (i in 0..stores.size-1){
            for (j in i+1..stores.size-1){

                val condition = when (flag) {
                    1 -> stores[i].data?.downloads?.total!! > stores[j].data?.downloads?.total!!
                    2 -> stores[i].data?.sessions?.total!! > stores[j].data?.sessions?.total!!
                    3 -> stores[i].data?.totalSale?.total!! > stores[j].data?.totalSale?.total!!
                    else -> stores[i].data?.addToCart?.total!! > stores[j].data?.addToCart?.total!!
                }
                if(condition) {//sort by ascending order
                    val temp = stores[i]
                    stores[i] = stores[j]
                    stores[j] = temp
                }
            }
        }
        showStores( stores.toList(),flag)
    }
    private fun showStores(stores: List<Store>,flag: Int) {
        list_item.layoutManager= LinearLayoutManager(activity)
        list_item.adapter = StoreAdapter(stores,flag)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater!!.inflate(R.menu.sort_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var flag = 1
        when(item!!.itemId){
            R.id.action_downloads -> {
                flag =1
                item.isChecked = true
                if(storeList !=null){
                    sortList(storeList.toTypedArray(),flag)
                }

            }

            R.id.action_session -> {
                flag =2
                item.isChecked = true
                if(storeList !=null){
                    sortList(storeList.toTypedArray(),flag)
                }
            }

            R.id.action_totalSale -> {
                flag=3
                item.isChecked = true
                if(storeList !=null){
                    sortList(storeList.toTypedArray(),flag)
                }
            }

            R.id.action_addToCart -> {
                flag=4
                item.isChecked = true
                if(storeList !=null){
                    sortList(storeList.toTypedArray(),flag)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    }

