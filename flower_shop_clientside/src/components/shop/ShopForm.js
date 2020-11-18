import React, {Component} from 'react'
import {localizedStrings} from '../util/localization'

import s from "../user/profile/Profile.module.css";
import {Button, Form, Input, notification} from "antd";

import {
    ERROR,
    SUCCESS,
    COMPANY_NAME_MAX_LENGTH,
    COMPANY_DESCRIPTION_MAX_LENGTH,
    COMPANY_LICENCE_NUM_MAX_LENGTH, EMAIL_MAX_LENGTH,

} from "../../constants";

import {updateCompanyInfoRequest} from "../util/utilsAPI";
import MapContainer from "../common/map/MapContainer";


class ShopForm extends Component {

    state = {
        shopAdmin: "",
        // this.props.currentCompany.shopAdmin,

        contacts: {
            firstPhoneNumber: {
                value: "+375291234567"
            },
            secondPhoneNumber: {
                value: "+375291234567"
            },
            email: {
                value: "+company@hu.wu"
            },
            city: {
                value: "Minsk"
            },
            address: {
                value: "Porshneva str. 48-987"
            }
        },
        workingHours: {
            hours: " s 10 do 18"
        },

        isEditing: false

    }


    render() {

        return (

            <div>
                <div>

                    <div>
                        <Form>
                            <Form.Item
                                className={s.formItem}
                                label={'Номер телефона'}
                                validateStatus={this.state.contacts.firstPhoneNumber.validateStatus}
                                onChange={(event) => this.handleInputChange(event, this.validatePhoneNumber)}
                                help={this.state.contacts.firstPhoneNumber.errorMsg}>
                                <Input
                                    name="firstPhoneNumber"
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.contacts.firstPhoneNumber.value}>
                                </Input>
                            </Form.Item>

                            <Form.Item
                                className={s.formItem}
                                label={'Емаил магазина'}
                                validateStatus={this.state.contacts.email.validateStatus}
                                onChange={(event) => this.handleInputChange(event, this.validateEmail)}
                                help={this.state.contacts.email.errorMsg}>
                                <Input
                                    name="email"
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.contacts.email.value}>
                                </Input>
                            </Form.Item>

                            <Form.Item
                                className={s.formItem}
                                label={'Рабочие часы'}
                                validateStatus={this.state.workingHours.hours.validateStatus}
                                onChange={(event) => this.handleInputChange(event, this.validateWorkingHours)}
                                help={this.state.workingHours.hours.errorMsg}>
                                <Input
                                    name="email"
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.workingHours.hours.value}>
                                </Input>
                            </Form.Item>


                            <div className="row" style={{marginBottom: '15%'}}>
                                <div className="col">
                                    <MapContainer
                                        google={this.props.google}
                                        center={{lat: 53.893009, lng: 27.567444}}
                                        height='300px'
                                        zoom={14}
                                    />
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

        console.log('handle input change')
        console.log('inputName= ' + inputName)
        console.log('inputValue= ' + inputValue)

        this.setState({
            [inputName]: {
                value: inputValue,
                ...validationFun(inputValue)
            }
        })
    }


    validatePhoneNumber = (phoneNumber) => {

        console.log('phoneNumber for validation: ' + phoneNumber)

        if (phoneNumber === undefined || phoneNumber.length > COMPANY_LICENCE_NUM_MAX_LENGTH) {
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


    validateWorkingHours = (hours) => {

        console.log('hours for validation : ' + hours)

        if (hours === undefined) {
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

export default ShopForm
