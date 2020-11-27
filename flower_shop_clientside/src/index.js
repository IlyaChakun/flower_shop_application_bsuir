import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import App from './app/App'
import { debugContextDevtool } from 'react-context-devtool'
import { BrowserRouter } from 'react-router-dom'
// import 'antd/dist/antd.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/css/bootstrap-grid.css'

// todo если включить антд и выключить бутстрап - прокрутки нет - но и дизайн в жопе весь


const container = document.getElementById('root')

ReactDOM.render(
  <BrowserRouter>
    <App/>
  </BrowserRouter>, container)

const options = {
  debugReducer: false,
  debugContext: true,
  disable: false,
  disableAutoMode: false
}
// Attach root container
debugContextDevtool(container, options)
