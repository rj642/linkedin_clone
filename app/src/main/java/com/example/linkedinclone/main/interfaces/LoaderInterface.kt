package com.example.linkedinclone.main.interfaces

interface LoaderInterface {
    // TODO: Implement these function in MainActivity

    /**
     * @idle: This function will just hide the progress bar from activity
     */
    fun onIdle()

    /**
     * @load: This function will handle loading status, implement the same in MainActivity
     */
    fun onLoad()

    /**
     * @success: This function will handle success state, implement the same in MainActivity
     */
    fun onSuccess()

    /**
     * @failed: This function will handle failed state, implement the same in MainActivity
     */
    fun onFailed()

}