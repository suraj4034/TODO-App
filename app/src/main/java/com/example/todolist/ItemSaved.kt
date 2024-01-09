package com.example.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ItemSaved {
    val FILENAME="listinfo.dat" // this is the file name saved in the device memory
    //Below method is used to write data in to the file.
    fun writeData(item: ArrayList<String>, context: Context){
    var fosObj: FileOutputStream=context.openFileOutput(FILENAME, Context.MODE_PRIVATE)

    var oosObj=ObjectOutputStream(fosObj)
    oosObj.writeObject(item)
    oosObj.close()
    }
    //Below method is used to read data from the file.
    fun readData(context: Context) : ArrayList<String>
    {
        var itemList: ArrayList<String>
        try {
            var fisObj: FileInputStream= context.openFileInput(FILENAME)
            var oisObj=ObjectInputStream(fisObj)
            itemList= oisObj.readObject() as ArrayList<String>
        }
        catch (e : FileNotFoundException){
            itemList=ArrayList()
        }

       return itemList
    }

}