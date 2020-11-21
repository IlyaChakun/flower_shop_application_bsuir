import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import CompanyForm from '../company/CompanyForm'

import '../../index.css'

class Company extends Component {
  render () {
    return (
      <div className="container">
        <div className="col-sm-12">

          <div className="row">
            <CompanyForm/>
          </div>

          <div className="row">
            <p>
                      Наши менеджеры всегда окажут необходимую помощь по
                      телефону: <a
                href="tel:+375291456777">+375 (29) 1-456-777</a>.
            </p>
          </div>

        </div>
      </div>
    )
  }
}

export default withRouter(Company)
