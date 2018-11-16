var webpack = require('webpack');
var path = require('path');
var ParallelUglifyPlugin = require('webpack-parallel-uglify-plugin');
var CompressionWebpackPlugin = require('compression-webpack-plugin');

module.exports = {
  entry: [
    path.resolve(__dirname, 'react/main.js'),
  ],
  output: {
    path: __dirname + '/webapp/build',
    publicPath: '/build/',
    filename: './bundle.js',
    chunkFilename: "[id].chunk.js"
  },
  module: {
    loaders: [{
      test: /\.css$/,
      loader: 'style-loader!css-loader'
    }, {
      test: /\.js[x]?$/,
      include: path.resolve(__dirname, 'react'),
      loader: 'babel',
      query: {compact: false} 
    }, {
      test: /\.(png|jpg)$/,
      loader: 'url-loader?limit=8192'
    }, {
      test: /\.(woff|woff2|eot|ttf|svg)(\?.*$|$)/,
      loader: 'url'
    }]
  },
  resolve: {
    extensions: ['', '.js', '.jsx'],
  },
  plugins: [
    new webpack.optimize.DedupePlugin(),
    new ParallelUglifyPlugin({
      cacheDir: '.cache/',
      uglifyJS: {
        output: {
          comments: false //不需要注释
        },
        sourceMap: false, //不要代码地图
        compress: {
          warnings: false //不需要警告
        }
      }
    }),
    new CompressionWebpackPlugin({ //gzip 压缩
      asset: '[path].gz[query]',
      algorithm: 'gzip',
      test: new RegExp(
        '\\.(js|css)$'    //压缩 js 与 css
      ),
      threshold: 10240,
      minRatio: 0.8
    }),
     ]
};