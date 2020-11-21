import React, {Component} from 'react'
import {localizedStrings} from '../util/localization'

import s from "./Company.module.css";
import  {Button, Form, Input, notification} from "antd";


import {
    SUCCESS,

} from "../../constants";

import {
    getCurrentCompanyRequest,
    saveCompanyRequest,
    saveReviewRequest,
    updateCompanyInfoRequest
} from "../util/utilsAPI";
import MapContainer from "../common/map/MapContainer";
import {
    validateAddress,
    validateCity,
    validateEmail,
    validatePhoneNumber, validateText
} from "../common/validation/ValidationFunctions";
import {
    validateBankName,
    validateCheckingAccount,
    validateIBAN, validateLicenceNumber, validateName, validatePayerAccountNumber,
    validatePostalCode
} from "./CompanyValidationFunctions";

const { TextArea } = Input;

const layout = {
    labelCol: {
        span: 9,
    },
    wrapperCol: {
        span: 15,
    },
}

class CompanyForm extends Component {

    state = {
        shopAdmin: "",

        name: {value: ""},
        description: {value: ""},
        licenceNumber: {value: ""},

        firstPhoneNumber: {value: ""},
        secondPhoneNumber: {value: ""},
        email: {value: ""},
        city: {value: ""},
        address: {value: ""},
        payerAccountNumber: {value: ""},
        checkingAccount: {value: ""},
        bankName: {value: ""},
        bankCode: {value: ""},
        postalCode: {value: ""},
        bankAddress: {value: ""},
        shops: [],

        isEditing: false
    }


    componentDidMount() {
        this.resolveCompany()
    }

    resolveCompany() {
        const company = getCurrentCompanyRequest()
        company
            .then(response => {
                this.setState({
                    name: {value: response.name,},
                    description: {value: response.description},
                    licenceNumber: {value: response.licenceNumber},

                    firstPhoneNumber: {value: response.contacts.firstPhoneNumber},
                    secondPhoneNumber: {value: response.contacts.secondPhoneNumber},
                    email: {value: response.contacts.email},
                    city: {value: response.contacts.city},
                    address: {value: response.contacts.address},
                    payerAccountNumber: {value: response.companyLegalAddress.payerAccountNumber},
                    checkingAccount: {value: response.companyLegalAddress.checkingAccount},
                    bankName: {value: response.companyLegalAddress.bankInformation.bankName},
                    bankCode: {value: response.companyLegalAddress.bankInformation.bankCode},
                    postalCode: {value: response.companyLegalAddress.bankInformation.postalCode},
                    bankAddress: {value: response.companyLegalAddress.bankInformation.address},
                    shops: {...response.shops}
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


    editCompany = () => {
        if (this.state.isEditing === false) {
            console.log('Start Edit company')
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
        const companyRequest = {
            "name": this.state.name.value,
            "description": this.state.description.value,
            "licenceNumber": this.state.licenceNumber.value,
            "contacts": {
                "firstPhoneNumber": this.state.firstPhoneNumber.value,
                "secondPhoneNumber": this.state.secondPhoneNumber.value,
                "email": this.state.email.value,
                "city": this.state.city.value,
                "address": this.state.address.value,
            },
            "companyLegalAddress": {
                "payerAccountNumber": this.state.payerAccountNumber.value,
                "checkingAccount": this.state.checkingAccount.value,
                "bankInformation": {
                    "bankName": this.state.bankName.value,
                    "bankCode": this.state.bankCode.value,
                    "postalCode": this.state.postalCode.value,
                    "address": this.state.bankAddress.value
                }
            }
        }

        console.log('company request: ' + companyRequest)

        saveCompanyRequest(companyRequest)
            .then(() => {
                notification.success({
                    message: localizedStrings.alertAppName,
                    description: 'Компания сохранена!',
                })

            }).catch(error => {
            notification.error({
                message: localizedStrings.alertAppName,
                description: 'Чет пошло не так. сорян'
            })
        })
    }


    isFormInvalid = () => {
        return !(this.state.name.validateStatus === SUCCESS &&
            this.state.licenceNumber.validateStatus === SUCCESS &&
            this.state.firstPhoneNumber.validateStatus === SUCCESS
        )
    }

    render() {

        return (

            <Form {...layout}
                  onFinish={this.handleSubmit} className={s.form}>

                <div className="row mb-5">
                    <div className="col-sm-6">
                        <Form.Item
                            className={s.formItem}
                            label={localizedStrings.companyName}
                            validateStatus={this.state.name.validateStatus}
                            hasFeedback
                            onChange={(event) => this.handleInputChange(event, validateName)}
                            help={this.state.name.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите название компании!',
                                },
                            ]}
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
                            label={'УНП'}
                            validateStatus={this.state.payerAccountNumber.validateStatus}
                            onChange={(event) => this.handleInputChange(event, validatePayerAccountNumber)}
                            help={this.state.payerAccountNumber.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите Учетный номер плательщика!',
                                },
                            ]}
                        >
                            <Input
                                name="payerAccountNumber"
                                size="middle"
                                disabled={!this.state.isEditing}
                                value={this.state.payerAccountNumber.value}>
                            </Input>
                        </Form.Item>

                        <Form.Item
                            className={s.formItem}
                            label={localizedStrings.companyLicenceNumber}
                            validateStatus={this.state.licenceNumber.validateStatus}
                            hasFeedback
                            onChange={(event) => this.handleInputChange(event, validateLicenceNumber)}
                            help={this.state.licenceNumber.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите номер вашей лецензии!',
                                },
                            ]}
                        >
                            <Input
                                name="licenceNumber"
                                size="middle"
                                disabled={!this.state.isEditing}
                                value={this.state.licenceNumber.value}>
                            </Input>
                        </Form.Item>
                    </div>
                    <div className="col-sm-6">
                        <Form.Item
                            className={s.formItem}
                            label={localizedStrings.companyDescription}
                            validateStatus={this.state.description.validateStatus}
                            hasFeedback
                            onChange={(event) => this.handleInputChange(event, validateText)}
                            help={this.state.description.errorMsg}>
                            <TextArea
                                rows={5}
                                name="description"
                                size="middle"
                                disabled={!this.state.isEditing}
                                value={this.state.description.value}>
                            </TextArea>
                        </Form.Item>
                    </div>
                </div>

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
                                    message: 'Пожалуйста, введите город компании!',
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
                            label={'Адрес компании'}
                            validateStatus={this.state.address.validateStatus}
                            onChange={(event) => this.handleInputChange(event, validateAddress)}
                            help={this.state.address.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите адрес компании!',
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
                            label={'Емаил конторы'}
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
                    </div>

                    <div className="col-sm-6">
                        <Form.Item
                            className={s.formItem}
                            label={'Банк'}
                            validateStatus={this.state.bankName.validateStatus}
                            onChange={(event) => this.handleInputChange(event, validateBankName)}
                            help={this.state.bankName.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите полное название банка!',
                                },
                            ]}
                        >
                            <Input
                                name="bankName"
                                size="middle"
                                disabled={!this.state.isEditing}
                                value={this.state.bankName.value}>
                            </Input>
                        </Form.Item>

                        <Form.Item
                            className={s.formItem}
                            label={'IBAN'}
                            validateStatus={this.state.bankCode.validateStatus}
                            onChange={(event) => this.handleInputChange(event, validateIBAN)}
                            help={this.state.bankCode.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите IBAN!',
                                },
                            ]}
                        >
                            <Input
                                name="bankCode"
                                size="middle"
                                disabled={!this.state.isEditing}
                                value={this.state.bankCode.value}>
                            </Input>
                        </Form.Item>

                        <Form.Item
                            className={s.formItem}
                            label={'Расчетный счет'}
                            validateStatus={this.state.checkingAccount.validateStatus}
                            onChange={(event) => this.handleInputChange(event, validateCheckingAccount)}
                            help={this.state.checkingAccount.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите расчетный счет!',
                                },
                            ]}
                        >
                            <Input
                                name="checkingAccount"
                                size="middle"
                                disabled={!this.state.isEditing}
                                value={this.state.checkingAccount.value}>
                            </Input>
                        </Form.Item>


                        <Form.Item
                            className={s.formItem}
                            label={'Адрес банка'}
                            validateStatus={this.state.address.validateStatus}
                            onChange={(event) => this.handleInputChange(event, validateAddress)}
                            help={this.state.address.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите адрес банка!',
                                },
                            ]}
                        >
                            <Input
                                name="bankAddress"
                                size="middle"
                                disabled={!this.state.isEditing}
                                value={this.state.address.value}>
                            </Input>
                        </Form.Item>

                        <Form.Item
                            className={s.formItem}
                            label={'Почтовый индекс банка'}
                            validateStatus={this.state.postalCode.validateStatus}
                            onChange={(event) => this.handleInputChange(event, validatePostalCode)}
                            help={this.state.postalCode.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите почтовый индекс банка!',
                                },
                            ]}
                        >
                            <Input
                                name="postalCode"
                                size="middle"
                                disabled={!this.state.isEditing}
                                value={this.state.postalCode.value}>
                            </Input>
                        </Form.Item>
                    </div>
                </div>

                <div className="row">
                    <div className="col-8"></div>
                    <div className="col-4">
                        <Form.Item className={s.formItem}>
                            <Button
                                onClick={this.editCompany}
                                size="large"
                                className={s.button}>
                                Изменить компанию
                            </Button>
                            <Button
                                type="primary"
                                htmlType="submit"
                                size="large"
                                className={s.button}
                                disabled={this.isFormInvalid()}>
                                Сохранить компанию
                            </Button>
                        </Form.Item>
                    </div>
                </div>
            </Form>
        )
    }

}

export default CompanyForm
