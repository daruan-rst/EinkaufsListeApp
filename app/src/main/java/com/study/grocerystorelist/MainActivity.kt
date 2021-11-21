package com.study.grocerystorelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemLongClickListener


abstract class MainActivity : AppCompatActivity() {


    private var adapter : ArrayAdapter<String>? = null
    abstract var t : Toast
    private var items : ArrayList<String> = ArrayList()
    private var listView : ListView =  findViewById(R.id.list_view)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var input : EditText = findViewById(R.id.input);
        var enter: ImageView = findViewById(R.id.add);


         items.add("Apfel")
         items.add("Tomate")
         items.add("Karrote")
         items.add("Spinat")
         items.add("Kopfsalat")
         items.add("Kohl")
         items.add("Brokkoli")


        listView.onItemClickListener =
            AdapterView.OnItemClickListener{ adapterView, view, i, l ->
                var name :String = items.get(i)
                makeToast(name)
            }

        enter.setOnClickListener(View.OnClickListener {
            val text = input.getText().toString()
            if (text.isEmpty()) {
                makeToast("Enter an item.")
            } else {
                addItem(text)
                with(input) { this.setText("") }
                makeToast("Added $text")
            }
        })

        listView.onItemLongClickListener =
            OnItemLongClickListener { adapterView, view, i, l ->
                removeItem(i)
                false
            }




        adapter = ArrayAdapter(applicationContext,android.R.layout.simple_expandable_list_item_1, items)
        listView.setAdapter(adapter)
    }

    open fun makeToast(s: String) {
        t.cancel()
        t = Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT)
        t.show()
    }

    open fun addItem(item: String?) {
        items.add(item!!)
        listView.adapter = adapter
    }

    open fun removeItem(i: Int) {
        makeToast("Removed: " + items.get(i))
        items.removeAt(i)
        listView.setAdapter(adapter)
    }


}