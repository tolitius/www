# www

Creates a new Clojure + ClojureScript (+ nREPL) + Compojure + Ring project template

## what it does

There are two different templates **www** can create:

* a simple barebone template 
* a template with a browser connected ClojureScript nREPL (via [austin](https://github.com/cemerick/austin))

Let's look at both.

###Simple www Template

```
lein new www [app name]
```

will create a project template in a form of:

```bash
[app name]
├── project.clj
├── resources
│   └── public
│       └── css
│           └── [app name].css   # entry point for CSS
├── src
│   └── [app name]
│       ├── cljs
│       │   ├── tools.cljs       # console logging & local storage
│       │   └── [app name].cljs  # entry point for ClojureScript
│       ├── handler.clj          # entry point for Compojure routes
│       ├── layout.clj           # sample layout (used as an example is "handler.clj")
│       └── [app name].clj       # entry point for server logic
└── test
    └── [app name]
        └── test.clj             # entry point for server side tests
```

which can be simply run by `cd [app name]` and:

```
lein ring server
```

which will compile (with ClojureScript) and start the app:

<p align="center">
  <img src="https://github.com/tolitius/www/raw/master/docs/www-run.png" alt="running www"/>
</p>

app is ready to roll.

a more interesting option is being able to feel/work with DOM from within a CLJS REPL, let's see how it's done.

###Browser Connected ClojureScript REPL www Template

this template is created the same way as a simple one above plus a handy `with-brepl` option:

```
lein new www [app name] with-brepl
```

which creates a project template in a form of:

```bash
[app name]
├── project.clj
├── resources
│   └── public
│       └── css
│           └── [app name].css   # entry point for CSS
├── src
│   └── [app name]
│       ├── cljs
│       │   ├── tools.cljs       # console logging & local storage
│       │   └── [app name].cljs  # entry point for ClojureScript
│       ├── handler.clj          # entry point for Compojure routes
│       ├── layout.clj           # sample layout (used as an example is "handler.clj")
│       └── [app name].clj       # entry point for server logic
└── test
    └── [app name]
        ├── brepl.clj            # configures and starts connectable cljs repl
        └── test.clj             # entry point for server side tests
```

now `cd [app name]` and:

```
lein repl
```

once inside the Clojure REPL connect it to the browser by:

```clojure
user=> (connect-to-browser)
2014-03-05 22:13:39.954:INFO:oejs.Server:jetty-7.6.8.v20121106
2014-03-05 22:13:39.979:INFO:oejs.AbstractConnector:Started SelectChannelConnector@0.0.0.0:3000
Browser-REPL ready @ http://localhost:54698/6389/repl/start
Type `:cljs/quit` to stop the ClojureScript REPL
nil
cljs.user=>
```

open a browser at [http://localhost:3000/](http://localhost:3000/), it will make it connect to this REPL

####and now... show time! 

let's change DOM from within our very own CLJS REPL:

```clojure
cljs.user=> (def $container (.getElementById js/document "container"))
#<[object HTMLDivElement]>

cljs.user=> (set! (.-innerHTML $container) "I am ready <em>and</em> I am being created!")
```

no need to refresh the browser, since it works directly on the DOM:

<p align="center">
  <img src="https://github.com/tolitius/www/raw/master/docs/via-brepl.png" alt="Changing DOM with CLJS REPL"/>
</p>

### LightTable Style (live) Coding

Since connecting a ClojureScript REPL to a browser relies on [austin](https://github.com/cemerick/austin) which relies on [piggieback](https://github.com/cemerick/piggieback), projects created with **www** template can have their "nREPL ready" ClojureScript editors / IDEs also connected to a browser.

####VIM as an example

Same should work in emacs, eclipse, intellij, since the main ingredient here is nREPL and they all support it, but we'll start from VIM as an example.

This story is about using a great VIM plugin [vim-fireplace](https://github.com/tpope/vim-fireplace) that makes VIM a joy hacking Clojure/Script platform.

Here is how "to lighttable" (used as a verb here) a project created with **www**:

* create a new project

```clojure
lein new www [app name] with-brepl
cd [app name]
```

* start repl and connect it to a browser

```clojure
lein repl
```
```clojure
user=> (connect-to-browser)
2014-04-24 15:33:52.394:INFO:oejs.Server:jetty-7.6.8.v20121106
2014-04-24 15:33:52.416:INFO:oejs.AbstractConnector:Started SelectChannelConnector@0.0.0.0:3000
Browser-REPL ready @ http://localhost:55007/6594/repl/start
Type `:cljs/quit` to stop the ClojureScript REPL
nil
cljs.user=>
```

* open a ClojureScript file of interest, and connect VIM to a "browser repl environemnt"

```bash
$ vi src/[app name]/cljs/[app name].cljs
```

in VIM command mode run:

```vim
:Piggieback (browser-repl-env)
```

* open the browser `http://localhost:3000/`:

<p align="center">
  <img src="https://github.com/tolitius/www/raw/master/docs/vim-piggieback.png" alt="piggieback from vim-fireplace"/>
</p>

Ready to roll! All the changes in the ClojureScript file be reflected in the browser with no refresh.
In order to do that, evaluate a changed piece of code (it's top level form) with `cpp` (vim-fireplace lingo):

<p align="center">
  <img src="https://github.com/tolitius/www/raw/master/docs/vim-clojurescript-cpp.png" alt="evaluate clojurescript in vim"/>
</p>

Manipulate DOM? Of course:

<p align="center">
  <img src="https://github.com/tolitius/www/raw/master/docs/vim-instant-dom.png" alt="evaluate DOM changes in vim"/>
</p>

## license

copyright © 2014 tolitius

distributed under the Eclipse Public License, the same as Clojure.
