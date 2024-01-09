package com.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var item : EditText
    lateinit var addButton : Button
    lateinit var listView : ListView
    var itemList =ArrayList<String>()

    var itemSaved= ItemSaved()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        item=findViewById(R.id.editText)
        addButton=findViewById(R.id.buttonADD)
        listView=findViewById(R.id.list)

        // this method is read the data and send to the array list.
        itemList=itemSaved.readData(this)

        //send the data to the list view using ArrayAdapter
        var arrayAdapter=ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList)
       listView.adapter=arrayAdapter

       addButton.setOnClickListener {
           var itemName: String=item.text.toString()
           itemList.add(itemName)
           item.setText("")
           itemSaved.writeData(itemList,applicationContext)
           arrayAdapter.notifyDataSetChanged()
       }
        listView.setOnItemClickListener { adapterView, view, position, l ->
            var alert= AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Do You Want to delete this item from the list ? ")
            alert.setCancelable(false)
            alert.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                itemSaved.writeData(itemList,applicationContext)
            })
            alert.create().show()
        }

    }
}