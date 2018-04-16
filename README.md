To Do
-----
o fretboard: tuning spinner
  . tuning spinner should reflect default-tuning the setup code defaults to  
  . spinner changes should update guitar object and cause fretboard redraw
. guidance: key spinner
. guidance: scale spinner
. guidance: add colors and labels to notes
. fretboard: implement capo'ing (spinner, etc.)
. fretboard: handle note selection

. implement drag/panning (framework code is already in place)
. default scaling should be fit-to-width (fit fretboard length, not entire scale length)
. implement cross-fragment communication for the spinners
. info: handle info fragment
. selector: implement selector fragment
. chords: implement chords fragment
. implement transport controls and timeline


Guitar Measurements
-------------------
When building the Larrivee, the measurements are in inches.  But then those measurements are used
directly in drawing comands that take pixels.  This is fine for now, the scaling defaults make
it work (we scale up somewhere between 750% to 2000%).  But ideally we'd have the guitar-measurments,
the display density, and the scaling factor be independent from each other.  

Logging
-------
We use Anko logging.  Simply have your class extend AnkoLogger and then you can do:

```
info("message here")
```

> NOTE: `debug()` and `verbose()` do not work. Use `info()` or above for now.
>
> AnkoLogger checks Log.isLoggable() before issuing a logging statement.  Log.isLoggable's default
> is to return true for INFO and above only (effectively filtering out DEBUG and VERBOSE).  This is
> supposed to be overridable by setting the system property "log.tag" but I cannot figure out how to
> do that from an Android Studio run configuration or local property file.
>
> See:  
>   https://github.com/Kotlin/anko/issues/489  
>   https://stackoverflow.com/questions/44931325/anko-logging-with-verbose-and-debug-isnt-working
