# cccp

Creates a new Clojure + ClojureScript + Compojure + Ring project template

The reason it is not CCC<b>R</b>, is simply because [CCCP](http://en.wikipedia.org/wiki/Soviet_Union) is a Russian acronym fo USSR 

## what it does

Creates a project template in a form of:

```clojure
.
├── project.clj
├── resources
│   └── public
│       └── css
│           └── [app name].css   ;; entry point for CSS
└── src
    └── [app name]
        ├── cljs
        │   ├── [app name].cljs  ;; entry point for ClojureScript
        │   └── tools.cljs       ;; console logging & local storage
        ├── handler.clj          ;; entry point for Compojure routes
        ├── [app name].clj       ;; entry point for server logic
        └── layout.clj           ;; sample layout (used as an example is "handler.clj")
```

### start the app

```
lein ring server
```

which will compile (with ClojureScript) and start the app:

<p align="center">
  <img src="https://github.com/tolitius/cccp/raw/master/docs/cccp-run.png" alt="running CCCP"/>
</p>

### ClojureScript auto recompile

In a different terminal cd into the project root and:

```
lein cljsbuild auto
```

## usage

```clojure
lein new cccp [app name]
```

## license

copyright © 2014 tolitius

distributed under the Eclipse Public License, the same as Clojure.
