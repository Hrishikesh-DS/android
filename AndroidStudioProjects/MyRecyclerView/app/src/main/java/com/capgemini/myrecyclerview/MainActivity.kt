package com.capgemini.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listOfActor = mutableListOf<Actors>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfActor.add(Actors("Settings","https://i.pinimg.com/236x/5c/06/ca/5c06caa67fa7902f3c2b8ab5183ee2f1.jpg"))
        listOfActor.add(Actors("Instagram","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeLknlp6-WHekAx9pGFe2uBDa8HlyhrKaOkVGddh1lAFdqSxOc1iVXtNDN4BxI7ySVW0U&usqp=CAU"))
        listOfActor.add(Actors("Cloud","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNC2g4tPGxl0hmC6Xhm0rnyuVbifWx0yuclvS1_akb2CarhnB62ZrlqA_57HaUSyUCUWs&usqp=CAU"))
        listOfActor.add(Actors("Notes","https://i.pinimg.com/236x/0c/57/30/0c573089aa98e35c58c5aceb4416cebb.jpg"))
        listOfActor.add(Actors("Photos","https://i.pinimg.com/236x/f5/02/57/f50257fa00eb8119d250757da2f8ffef.jpg"))
        listOfActor.add(Actors("Settings","https://i.pinimg.com/236x/5c/06/ca/5c06caa67fa7902f3c2b8ab5183ee2f1.jpg"))
        listOfActor.add(Actors("Instagram","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeLknlp6-WHekAx9pGFe2uBDa8HlyhrKaOkVGddh1lAFdqSxOc1iVXtNDN4BxI7ySVW0U&usqp=CAU"))
        listOfActor.add(Actors("Cloud","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNC2g4tPGxl0hmC6Xhm0rnyuVbifWx0yuclvS1_akb2CarhnB62ZrlqA_57HaUSyUCUWs&usqp=CAU"))
        listOfActor.add(Actors("Notes","https://i.pinimg.com/236x/0c/57/30/0c573089aa98e35c58c5aceb4416cebb.jpg"))
        listOfActor.add(Actors("Photos","https://i.pinimg.com/236x/f5/02/57/f50257fa00eb8119d250757da2f8ffef.jpg"))
        listOfActor.add(Actors("Settings","https://i.pinimg.com/236x/5c/06/ca/5c06caa67fa7902f3c2b8ab5183ee2f1.jpg"))
        listOfActor.add(Actors("Instagram","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeLknlp6-WHekAx9pGFe2uBDa8HlyhrKaOkVGddh1lAFdqSxOc1iVXtNDN4BxI7ySVW0U&usqp=CAU"))
        listOfActor.add(Actors("Cloud","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNC2g4tPGxl0hmC6Xhm0rnyuVbifWx0yuclvS1_akb2CarhnB62ZrlqA_57HaUSyUCUWs&usqp=CAU"))
        listOfActor.add(Actors("Notes","https://i.pinimg.com/236x/0c/57/30/0c573089aa98e35c58c5aceb4416cebb.jpg"))
        listOfActor.add(Actors("Photos","https://i.pinimg.com/236x/f5/02/57/f50257fa00eb8119d250757da2f8ffef.jpg"))

        rView.layoutManager = GridLayoutManager(this,2)
        rView.adapter=ActorAdapter(listOfActor)
    }
}