const webpack = require('webpack')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const precss = require('precss')
const postcssPresetEnv = require('postcss-preset-env');

const path = require('path')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')

const devMode = true

module.exports = {
    entry: './src/index.js',
    output: {
        path: path.join(__dirname, '/build'),
        filename: 'bundle.js'
    },

    module: {
        rules: [

            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                loader: 'babel-loader'
            },
            {
                test: /\.less$/,
                use: [
                    {loader: 'style-loader'},
                    {loader: 'css-loader'},
                    {loader: 'less-loader', options: {javascriptEnabled: true}}
                ]
            },
            {
                test: /\.style.js$/,
                use: [
                    'style-loader',
                    { loader: 'css-loader', options: { importLoaders: 2 } },
                    { loader: 'postcss-loader', options: { parser: 'postcss-js' } },
                    'babel-loader'
                ]
            },
            {
                test: /\.css$/i,
                use: [
                    'style-loader',
                    { loader: 'css-loader', options: { importLoaders: 1 } },
                    { loader: 'postcss-loader', options: {
                            ident: 'postcss',
                            plugins: () => [
                                postcssPresetEnv({
                                    autoprefixer: { grid: true }
                                })
                            ]
                        } }
                ]
            },

            {
                test: /\.(png|jpe?g|gif|svg|eot|ttf|woff|woff2)$/i,
                loader: 'url-loader',
                options: {
                    limit: 8192
                }
            },


        ]
    },
    resolve: {
        extensions: ['.js', '.jsx', '.scss', '.css', '.svg', '.less']
    },

    plugins: [

        new HtmlWebpackPlugin({
            template: './public/index.html'
        }),

        new MiniCssExtractPlugin({
            filename: devMode ? '[name].css' : '[name].[hash].css',
            chunkFilename: devMode ? '[id].css' : '[id].[hash].css'
        }),

        new webpack.LoaderOptionsPlugin({options: {postcss: [precss]}})
    ]
}
