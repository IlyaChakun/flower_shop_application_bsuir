import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import App from './app/App'
import {BrowserRouter} from 'react-router-dom'
// import 'antd/dist/antd.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/css/bootstrap-grid.css'
import {Provider} from 'react-redux'
import store from './redux/store'

// todo если включить антд и выключить бутстрап - прокрутки нет - но и дизайн в жопе весь


ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </Provider>
  , document.getElementById('root'))
