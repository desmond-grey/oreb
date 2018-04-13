Logging
-------
We use Anko logging.  Simply have your class extend AnkoLogger and then you can do:

```
info("message here")
```

> NOTE: `debug()` and `verbose()` do not work. Using `info()` or above for now.
>
> AnkoLogger checks Log.isLoggable() before issuing a logging statement.  Log.isLoggable's default
> is to return true for INFO and above only (effectively filtering out DEBUG and VERBOSE).  This is
> supposed to be overridable by setting the system property "log.tag" but I cannot figure out how to
> do that from an Android Studio run configuration or local property file.
>
> See:
>   https://github.com/Kotlin/anko/issues/489
>   https://stackoverflow.com/questions/44931325/anko-logging-with-verbose-and-debug-isnt-working