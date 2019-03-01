List GitHub Repos using Paging Library
======================================

This application is created to show how paging library can be used to load infinite lists
and used in conjunction with Network fetch & Room Database.

Tech Stack Used
---------------
1. **Android Architecture Components** (Room Database, LiveData, Paging Library(PagedList, PagedListAdapter)).
2. Application Architecture is **MVVM**.
3. Networking using [Retrofit 2](https://github.com/square/retrofit).
4. Used **Kotlin**.
5. Used **Kotlin Coroutines** for Async Operations.
6. Used [Retrofit 2 Adapter for Kotlin Coroutine's Deferred type](https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter).
7. Whole project is based on [AndroidX](https://developer.android.com/jetpack/androidx/).
8. Used [Dagger 2](https://github.com/google/dagger) for Dependency Injection.
9. [Picasso](https://github.com/square/picasso) for Image handling.


Steps
-----

1. Request the GitHub API to show [public repositories][1] and parse the JSON
   response.
2. Display a list of repositories, each entry shows
    - full repo name
    - description
    - login of the owner
    - forked or not
    - repo owner's avatar image
3. Requesting 15 repos at a time over the netwokr. Using an endless list with a load more mechanism. The
   load more is triggered when the scrolling is 5-items near to reaching the end of the 
   list.
4. Cache the repos in room database storage.
5. Show a light green background if the `fork` flag is false or missing, a white one
   otherwise.
6. On a long click on a list item showing a bottom sheet to ask user if go to repository `html_url` or
   owner `html_url` which is then opened in the browser.


TODOs
------------
- Implement a mechanism to download and store the repos on a regular interval also if the app is closed.


  [1]: https://api.github.com/users/xing/repos
