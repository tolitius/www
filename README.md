# cccp

Creates a new Clojure + ClojureScript + Compojure + Ring project template

The reason it is not CCC<b>R</b>, is simply because [CCCP](http://en.wikipedia.org/wiki/Soviet_Union) is a Russian acronym fo USSR 

## what it does

There are two different templates **cccp** can create:

* a simple barebone template 
* a template with a browser connected ClojureScript REPL (via [austin](https://github.com/cemerick/austin))

Let's look at both.

###Simple cccp Template

```
lein new cccp [app name]
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
  <img src="https://github.com/tolitius/cccp/raw/master/docs/cccp-run.png" alt="running CCCP"/>
</p>

app is ready to roll.

a more interesting option is being able to feel/work with DOM from within a CLJS REPL, let's see how it's done.

###Browser Connected ClojureScript REPL cccp Template

this template is created the same way as a simple one above plus a handy `:with-brepl` option:

```
lein new cccp [app name] :with-brepl
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

once inside the Clojure REPL start the app with CLJS REPL (`foo` here is your `[app name]`):

```clojure
xyz=> (require '[foo.brepl :refer [with-repl]])
xyz=> (with-repl)
2014-03-05 22:13:39.954:INFO:oejs.Server:jetty-7.6.8.v20121106
2014-03-05 22:13:39.979:INFO:oejs.AbstractConnector:Started SelectChannelConnector@0.0.0.0:3000
```

open a browser at [http://localhost:3000/](http://localhost:3000/), it will make it connect to this REPL

####and now... show time! 

let's change DOM from within our very own CLJS REPL:

```clojure
cljs.user=> (def $container (.getElementById js/document "container"))
#<[object HTMLDivElement]>

cljs.user=> (set! (.-innerHTML $container) "I am ready <em>and</em> I am being created!")
```
<p align="center">
  <img src="https://github.com/tolitius/cccp/raw/master/docs/via-brepl.png" alt="Changing DOM with CLJS REPL"/>
</p>

## license

copyright © 2014 tolitius

distributed under the Eclipse Public License, the same as Clojure.
