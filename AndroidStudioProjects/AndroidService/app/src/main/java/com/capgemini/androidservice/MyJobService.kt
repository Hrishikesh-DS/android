package com.capgemini.androidservice

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import kotlin.concurrent.thread

class MyJobService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        //execute task thread
        Toast.makeText(this,"Job started with ${params?.jobId}",Toast.LENGTH_SHORT).show()
        //if job id=1-> upload and if id=0-> download
        thread {
            //long running task
            Thread.sleep(2000)
            //after the task is complete
            jobFinished(params,false)//can be called inside thread
        }
        return true//should return true only when there's a long running task
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        //stop task
        Toast.makeText(this,"Job needs to be stopped..",Toast.LENGTH_SHORT).show()
        return false
    }

}