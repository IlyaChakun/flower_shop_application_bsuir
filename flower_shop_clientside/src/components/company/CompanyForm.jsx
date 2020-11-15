import React, {Component} from 'react'
import {localizedStrings} from '../util/localization'

import s from "../user/profile/Profile.module.css";
import {Button, Form, Input, notification} from "antd";

import {
    ERROR,
    SUCCESS,
    COMPANY_NAME_MAX_LENGTH,
    COMPANY_DESCRIPTION_MAX_LENGTH,
    COMPANY_LICENCE_NUM_MAX_LENGTH,

} from "../../constants";

import {updateCompanyInfoRequest} from "../util/utilsAPI";


class CompanyForm extends Component {

    state = {
        shopAdmin: "",
        // this.props.currentCompany.shopAdmin,

        name: {
            // value: this.props.currentCompany.name
            value: ""
        },
        description: {
            // value: this.props.currentCompany.description
            value: ""
        },
        licenceNumber: {
            // value: this.props.currentCompany.licenceNumber
            value: ""
        },

        contacts: "",
        // this.props.currentCompany.contacts,

        companyLegalAddress: "",
        // this.props.currentCompany.companyLegalAddress,

        shops: "",
        // this.props.currentCompany.shops,


        isEditing: false

    }

    editCompany = () => {
        if (this.state.isEditing === false) {
            console.log('Start Edit company')
            this.setState({
                isEditing: true
            })

        } else {
            console.log('Save Edited company')
            this.setState({
                isEditing: false
            })

            const updateCompanyRequest = {
                // id: this.state.currentUser.id,
                // name: this.state.name.value,
                // email: this.state.email.value,
            }

            console.log(updateCompanyRequest)

            updateCompanyInfoRequest(updateCompanyRequest)
                .then(() => {
                    notification.success({
                        message: localizedStrings.alertAppName,
                        description: localizedStrings.alertSuccessfulUserUpdate,
                    })
                    this.props.history.push('/profile/me')
                }).catch(error => {
                notification.error({
                    message: localizedStrings.alertAppName,
                    description: error.message || localizedStrings.alertException
                })
            })
        }

    }


    render() {

        return (

            <div >
                <div >

                    <div>
                        <Form>
                            <Form.Item
                                className={s.formItem}
                                label={localizedStrings.companyName}
                                validateStatus={this.state.name.validateStatus}
                                hasFeedback
                                onChange={(event) => this.handleInputChange(event, this.validateName)}
                                help={this.state.name.errorMsg}
                            >
                                <Input
                                    name="name"
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.name.value}>
                                </Input>
                            </Form.Item>

                            <Form.Item
                                className={s.formItem}
                                label={localizedStrings.companyLicenceNumber}
                                validateStatus={this.state.licenceNumber.validateStatus}
                                onChange={(event) => this.handleInputChange(event, this.validateLicenceNumber())}
                                help={this.state.licenceNumber.errorMsg}>
                                <Input
                                    name="licenceNumber"
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.licenceNumber.value}>
                                </Input>
                            </Form.Item>


                            <Form.Item
                                className={s.formItem}
                                label={localizedStrings.companyDescription}
                                validateStatus={this.state.description.validateStatus}
                                onChange={(event) => this.handleInputChange(event, this.validateDescription())}
                                help={this.state.description.errorMsg}>
                                <Input
                                    name="licenceNumber"
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.description.value}>
                                </Input>
                            </Form.Item>


                            <div className="row ">
                                <div className="col">
                                    <Button onClick={this.editCompany} className={s.button}>
                                        {localizedStrings.edit}
                                    </Button>
                                </div>

                            </div>

                        </Form>
                    </div>


                </div>

            </div>


        )
    }

    handleInputChange = (event, validationFun) => {
        const target = event.target
        const inputName = target.name
        const inputValue = target.value

        this.setState({
            [inputName]: {
                value: inputValue,
                ...validationFun(inputValue)
            }
        })
    }

    validateName = (name) => {
        if (!name) {
            return {
                validateStatus: ERROR,
                errorMsg: localizedStrings.alertCompanyBadNameEmpty
            }
        } else if (name.length > COMPANY_NAME_MAX_LENGTH) {
            return {
                validationStatus: ERROR,
                errorMsg: localizedStrings.alertBadNameTooLong
            }
        } else {
            return {
                validateStatus: SUCCESS,
                errorMsg: null,
            }
        }
    }


    validateDescription = (description) => {

        if (description.length > COMPANY_DESCRIPTION_MAX_LENGTH) {
            return {
                validateStatus: ERROR,
                errorMsg: localizedStrings.alertCompanyDescrTooLong
            }
        }

        return {
            validateStatus: null,
            errorMsg: null
        }
    }

    validateLicenceNumber = (number) => {

        if (number.length > COMPANY_LICENCE_NUM_MAX_LENGTH) {
            return {
                validateStatus: ERROR,
                errorMsg: localizedStrings.alertCompanyLicenceNumberTooLong
            }
        }

        return {
            validateStatus: null,
            errorMsg: null
        }
    }

}

export default CompanyForm
