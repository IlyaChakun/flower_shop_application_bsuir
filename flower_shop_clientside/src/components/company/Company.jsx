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
            <CompanyForm currentUser={this.props.currentUser}
              currentCompany={this.props.currentCompany} />
          </div>
        </div>
      </div>
    )
  }
}

export default withRouter(Company)
