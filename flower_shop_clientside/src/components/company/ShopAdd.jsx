import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'

import '../../index.css'
import {Button, Form, Input, notification} from "antd";
import s from "./Company.module.css";
import {localizedStrings} from "../util/localization";
import {
    validateAddress,
    validateCity, validateEmail,
    validatePhoneNumber
} from "../common/validation/ValidationFunctions";
import {getCurrentShopRequest, saveShopRequest} from "../util/utilsAPI";
import {ERROR, SUCCESS} from "../../constants";


const layout = {
    labelCol: {
        span: 9,
    },
    wrapperCol: {
        span: 15,
    },
}

class ShopAdd extends Component {
    state = {
        id: "",
        dateOfCreation: "",
        dateOfLastUpdate: "",

        firstPhoneNumber: {value: ""},
        secondPhoneNumber: {value: ""},
        email: {value: ""},
        city: {value: ""},
        address: {value: ""},
        workingHours: {value: ""},

        isEditing: false
    }

    componentDidMount() {
        this.resolveShop()
    }


    resolveShop() {
        const shop = getCurrentShopRequest()
        shop
            .then(response => {
                this.setState({

                    id: {value: response.id},

                    dateOfCreation: {value: response.dateOfCreation},
                    dateOfLastUpdate: {value: response.dateOfLastUpdate},

                    firstPhoneNumber: {value: response.contacts.firstPhoneNumber},
                    secondPhoneNumber: {value: response.contacts.secondPhoneNumber},
                    email: {value: response.contacts.email},
                    city: {value: response.contacts.city},
                    address: {value: response.contacts.address},

                    workingHours: {value: response.workingHours.hours}

                })
            })
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


    editShop = () => {
        if (this.state.isEditing === false) {
            console.log('Start Edit shop')
            this.setState({
                isEditing: true
            })

        } else {

            this.setState({
                isEditing: false
            })
            /*  console.log('Save Edited company')
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
              })*/
        }

    }

    handleSubmit = () => {
        const shopRequest = {

            "id": this.state.id,
            "dateOfCreation": this.state.dateOfCreation,
            "dateOfLastUpdate": this.state.dateOfLastUpdate,

            "contacts": {
                "firstPhoneNumber": this.state.firstPhoneNumber.value,
                "secondPhoneNumber": this.state.secondPhoneNumber.value,
                "email": this.state.email.value,
                "city": this.state.city.value,
                "address": this.state.address.value,
            },
            "workingHours": {
                "hours": this.state.workingHours.value
            }
        }

        console.log('shop request: ' + shopRequest)

        saveShopRequest(shopRequest)
            .then(() => {
                notification.success({
                    message: localizedStrings.alertAppName,
                    description: 'Магазин сохранен!',
                })

            }).catch(error => {
            notification.error({
                message: localizedStrings.alertAppName,
                description: 'Чет пошло не так. сорян'
            })
        })
    }


    isFormInvalid = () => {
        return !(this.state.firstPhoneNumber.validateStatus === SUCCESS &&
            this.state.secondPhoneNumber.validateStatus === SUCCESS &&
            this.state.email.validateStatus === SUCCESS &&
            this.state.city.validateStatus === SUCCESS &&
            this.state.address.validateStatus === SUCCESS &&
            this.state.workingHours.validateStatus === SUCCESS)
    }


    render() {
        return (
            <div className="container">
                <div className="col-sm-12">

                    <div className="row">
                        <Form {...layout}
                              onFinish={this.handleSubmit} className={s.form}>

                            <div className="row">
                                <div className="col-sm-6">

                                    <Form.Item
                                        className={s.formItem}
                                        label={'Город'}
                                        validateStatus={this.state.city.validateStatus}
                                        onChange={(event) => this.handleInputChange(event, validateCity)}
                                        help={this.state.city.errorMsg}
                                        rules={[
                                            {
                                                required: true,
                                                message: 'Пожалуйста, введите город магазина!',
                                            },
                                        ]}
                                    >
                                        <Input
                                            name="city"
                                            size="middle"
                                            disabled={!this.state.isEditing}
                                            value={this.state.city.value}>
                                        </Input>
                                    </Form.Item>

                                    <Form.Item
                                        className={s.formItem}
                                        label={'Адрес магазина'}
                                        validateStatus={this.state.address.validateStatus}
                                        onChange={(event) => this.handleInputChange(event, validateAddress)}
                                        help={this.state.address.errorMsg}
                                        rules={[
                                            {
                                                required: true,
                                                message: 'Пожалуйста, введите адрес магазина!',
                                            },
                                        ]}
                                    >
                                        <Input
                                            name="address"
                                            size="middle"
                                            disabled={!this.state.isEditing}
                                            value={this.state.address.value}>
                                        </Input>
                                    </Form.Item>

                                    <Form.Item
                                        className={s.formItem}
                                        label={'Номер телефона'}
                                        validateStatus={this.state.firstPhoneNumber.validateStatus}
                                        hasFeedback
                                        onChange={(event) => this.handleInputChange(event, validatePhoneNumber)}
                                        help={this.state.firstPhoneNumber.errorMsg}
                                        rules={[
                                            {
                                                required: true,
                                                message: 'Пожалуйста, введите контакный номер!',
                                            },
                                        ]}
                                    >
                                        <Input
                                            name="firstPhoneNumber"
                                            size="middle"
                                            disabled={!this.state.isEditing}
                                            value={this.state.firstPhoneNumber.value}>
                                        </Input>
                                    </Form.Item>

                                    <Form.Item
                                        className={s.formItem}
                                        label={'Второй номер телефона'}
                                        validateStatus={this.state.secondPhoneNumber.validateStatus}
                                        hasFeedback
                                        onChange={(event) => this.handleInputChange(event, validatePhoneNumber)}
                                        help={this.state.secondPhoneNumber.errorMsg}
                                    >
                                        <Input
                                            name="secondPhoneNumber"
                                            size="middle"
                                            disabled={!this.state.isEditing}
                                            value={this.state.secondPhoneNumber.value}>
                                        </Input>
                                    </Form.Item>

                                    <Form.Item
                                        className={s.formItem}
                                        label={'Емаил магазина'}
                                        validateStatus={this.state.email.validateStatus}
                                        hasFeedback
                                        onChange={(event) => this.handleInputChange(event, validateEmail)}
                                        help={this.state.email.errorMsg}
                                        rules={[
                                            {
                                                required: true,
                                                message: 'Пожалуйста, введите электронную почту!',
                                            },
                                        ]}
                                    >
                                        <Input
                                            name="email"
                                            size="middle"
                                            disabled={!this.state.isEditing}
                                            value={this.state.email.value}>
                                        </Input>
                                    </Form.Item>

                                    <Form.Item
                                        className={s.formItem}
                                        label={'Рабочее время магазина'}
                                        validateStatus={this.state.address.validateStatus}
                                        onChange={(event) => this.handleInputChange(event, this.validateWorkingHours)}
                                        help={this.state.address.errorMsg}
                                        rules={[
                                            {
                                                required: true,
                                                message: 'Пожалуйста, введите рабочее время магазина!',
                                            },
                                        ]}
                                    >
                                        <Input
                                            name="workingHours"
                                            size="middle"
                                            disabled={!this.state.isEditing}
                                            value={this.state.address.value}>
                                        </Input>
                                    </Form.Item>

                                </div>
                            </div>

                            <div className="row">
                                <div className="col-8"></div>
                                <div className="col-4">
                                    <Form.Item className={s.formItem}>
                                        <Button
                                            onClick={this.editShop}
                                            size="large"
                                            className={s.button}>
                                            Изменить магазин
                                        </Button>
                                        <Button
                                            type="primary"
                                            htmlType="submit"
                                            size="large"
                                            className={s.button}
                                            disabled={this.isFormInvalid()}>
                                            Сохранить магазин
                                        </Button>
                                    </Form.Item>
                                </div>
                            </div>
                        </Form>

                    </div>

                </div>
            </div>
        )
    }

    validateWorkingHours = (workingHours) => {
        if (workingHours.length > 15) {
            return {
                validateStatus: ERROR,
                errorMsg: 'Рабочее время магазина слишком длинное'
            }
        }

        return {
            validateStatus: SUCCESS,
            errorMsg: null
        }
    }
}


export default withRouter(ShopAdd)
