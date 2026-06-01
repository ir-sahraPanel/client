                    let config = {
                      mode: 'development',
                      resolve: {
                        modules: [
                          "node_modules"
                        ]
                      },
                      plugins: [],
                      module: {
                        rules: []
                      },
                      resolveLoader: {
  modules: ['node_modules', process.env['KOTLIN_TOOLING_DIR']]
}
                    };
                    
// entry
config.entry = {
    main: [require('path').resolve(__dirname, "kotlin/sahraPanel-webApp.mjs")]
};
config.output = {
    filename: (chunkData) => {
        return chunkData.chunk.name === 'main'
            ? "webApp.js"
            : "webApp-[name].js";
    },
    library: "webApp",
    libraryTarget: "umd",
    clean: true,
    globalObject: "globalThis"
};
// source maps
config.module.rules.push({
        test: /\.m?js$/,
        use: ["source-map-loader"],
        enforce: "pre"
});
config.devtool = 'eval-source-map';
config.ignoreWarnings = [
    /Failed to parse source map/,
    /Accessing import\.meta directly is unsupported \(only property access or destructuring is supported\)/
]

// dev server
config.devServer = {
  "open": true,
  "client": {
    "overlay": {
      "errors": true,
      "warnings": false
    }
  },
  "static": [
    {
      "directory": require('path').resolve(__dirname, "kotlin"),
      "watch": false
    },
    {
      "directory": require('path').resolve(__dirname, "../../../../webApp/build/processedResources/wasmJs/main"),
      "watch": false
    },
    {
      "directory": require('path').resolve(__dirname, "../../../.."),
      "watch": false
    }
  ]
};

                config.watchOptions = {
  "ignored": [
    "**/node_modules",
    "**/*.kt"
  ]
};
// noinspection JSUnnecessarySemicolon
;(function(config) {
    const tcErrorPlugin = require('kotlin-web-helpers/dist/tc-log-error-webpack');
    config.plugins.push(new tcErrorPlugin())
    config.stats = config.stats || {}
    Object.assign(config.stats, config.stats, {
        warnings: false,
        errors: false
    })
})(config);

// cors-headers.js
;(function(config) {
    // Required for SharedArrayBuffer / OPFS used by @sqlite.org/sqlite-wasm
    config.devServer = config.devServer || {};
    config.devServer.headers = [
        { key: 'Cross-Origin-Opener-Policy', value: 'same-origin' },
        { key: 'Cross-Origin-Embedder-Policy', value: 'require-corp' }
    ];
})(config);


config.experiments = {
    asyncWebAssembly: true,
    topLevelAwait: true,
}
module.exports = config
