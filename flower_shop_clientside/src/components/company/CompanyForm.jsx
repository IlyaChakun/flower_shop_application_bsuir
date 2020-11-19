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

import {
    getCurrentCompanyRequest,
    saveCompanyRequest,
    saveReviewRequest,
    updateCompanyInfoRequest
} from "../util/utilsAPI";
import MapContainer from "../common/map/MapContainer";
import {validateEmail, validatePhoneNumber} from "../common/validation/ValidationFunctions";


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

        name: {
            value: ""
        },
        description: {
            value: ""
        },
        licenceNumber: {
            value: ""
        },


        firstPhoneNumber: {
            value: ""
        },
        secondPhoneNumber: {
            value: ""
        },
        email: {
            value: ""
        },
        city: {
            value: ""
        },
        address: {
            value: ""
        },


        payerAccountNumber: {
            value: ""
        },
        checkingAccount: {
            value: ""
        },


        bankName: {
            value: ""
        },
        bankCode: {
            value: ""
        },
        postalCode: {
            value: ""
        },


        shops: "",
        // this.props.currentCompany.shops,


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
                    name: {
                        value: response.name,
                    },
                    description: {
                        value: response.description
                    },
                    licenceNumber: {
                        value: response.licenceNumber
                    },


                    firstPhoneNumber: {
                        value: response.contacts.firstPhoneNumber
                    },
                    secondPhoneNumber: {
                        value: response.contacts.secondPhoneNumber
                    },
                    email: {
                        value: response.contacts.email
                    },
                    city: {
                        value: response.contacts.city
                    },
                    address: {
                        value: response.contacts.address
                    },


                    payerAccountNumber: {
                        value: response.companyLegalAddress.payerAccountNumber
                    },
                    checkingAccount: {
                        value: response.companyLegalAddress.checkingAccount
                    },


                    bankName: {
                        value: response.companyLegalAddress.bankInformation.bankName
                    },
                    bankCode: {
                        value: response.companyLegalAddress.bankInformation.bankCode
                    },
                    postalCode: {
                        value: response.companyLegalAddress.bankInformation.postalCode
                    }
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
            "name": this.state.name,
            "description": this.state.description,
            "licenceNumber":this.state.licenceNumber,
            "contacts": {
                "firstPhoneNumber": this.state.firstPhoneNumber,
                "secondPhoneNumber": this.state.secondPhoneNumber,
                "email": this.state.email,
                "city": "Minsk",
                "address": "Porshneva str. 48-987"
            },
            "companyLegalAddress": {
                "payerAccountNumber": this.state.payerAccountNumber,
                "checkingAccount": this.state.checkingAccount,
                "bankInformation": {
                    "bankName": this.state.bankName,
                    "bankCode": this.state.bankCode,
                    "postalCode": this.state.postalCode,
                    "address": "Minsk for bank"
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

            <div>
                <div>

                    <div>
                        <Form {...layout}
                              onFinish={this.handleSubmit} className={s.form}>

                            {/*описание компании*/}
                            <Form.Item
                                className={s.formItem}
                                label={localizedStrings.companyName}
                                validateStatus={this.state.name.validateStatus}
                                hasFeedback
                                onChange={(event) => this.handleInputChange(event, this.validateName)}
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
                                label={localizedStrings.companyLicenceNumber}
                                validateStatus={this.state.licenceNumber.validateStatus}
                                hasFeedback
                                onChange={(event) => this.handleInputChange(event, this.validateLicenceNumber)}
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


                            <Form.Item
                                className={s.formItem}
                                label={localizedStrings.companyDescription}
                                validateStatus={this.state.description.validateStatus}
                                hasFeedback
                                onChange={(event) => this.handleInputChange(event, this.validateDescription)}
                                help={this.state.description.errorMsg}>
                                <Input
                                    name="description"
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.description.value}>
                                </Input>
                            </Form.Item>

                            {/*контакты компании*/}
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


                            {/*юр данные компании*/}

                            {/*<Form.Item*/}
                            {/*    className={s.formItem}*/}
                            {/*    label={'Учетный номер плательщика'}*/}
                            {/*    validateStatus={this.state.companyLegalAddress.payerAccountNumber.validateStatus}*/}
                            {/*    onChange={(event) => this.handleInputChange(event, validateEmail)}*/}
                            {/*    help={this.state.companyLegalAddress.payerAccountNumber.errorMsg}*/}
                            {/*    rules={[*/}
                            {/*        {*/}
                            {/*            required: true,*/}
                            {/*            message: 'Пожалуйста, введите Учетный номер плательщика!',*/}
                            {/*        },*/}
                            {/*    ]}*/}
                            {/*>*/}
                            {/*    <Input*/}
                            {/*        name="companyLegalAddress.payerAccountNumber"*/}
                            {/*        size="middle"*/}
                            {/*        disabled={!this.state.isEditing}*/}
                            {/*        value={this.state.companyLegalAddress.payerAccountNumber.value}>*/}
                            {/*    </Input>*/}
                            {/*</Form.Item>*/}

                            {/*<Form.Item*/}
                            {/*    className={s.formItem}*/}
                            {/*    label={'Расчетный счет'}*/}
                            {/*    validateStatus={this.state.companyLegalAddress.checkingAccount.validateStatus}*/}
                            {/*    onChange={(event) => this.handleInputChange(event, validateEmail)}*/}
                            {/*    help={this.state.companyLegalAddress.checkingAccount.errorMsg}*/}
                            {/*    rules={[*/}
                            {/*        {*/}
                            {/*            required: true,*/}
                            {/*            message: 'Пожалуйста, введите Расчетный счет!',*/}
                            {/*        },*/}
                            {/*    ]}*/}
                            {/*>*/}
                            {/*    <Input*/}
                            {/*        name="companyLegalAddress.checkingAccount"*/}
                            {/*        size="middle"*/}
                            {/*        disabled={!this.state.isEditing}*/}
                            {/*        value={this.state.companyLegalAddress.checkingAccount.value}>*/}
                            {/*    </Input>*/}
                            {/*</Form.Item>*/}


                            {/*банковские данные компании*/}
                            {/*<Form.Item*/}
                            {/*    className={s.formItem}*/}
                            {/*    label={'Имя банка сделать выпадающий список'}*/}
                            {/*    validateStatus={this.state.companyLegalAddress.bankInformation.bankName.validateStatus}*/}
                            {/*    onChange={(event) => this.handleInputChange(event, validateEmail)}*/}
                            {/*    help={this.state.companyLegalAddress.bankInformation.bankName.errorMsg}*/}
                            {/*    rules={[*/}
                            {/*        {*/}
                            {/*            required: true,*/}
                            {/*            message: 'Пожалуйста, введите Расчетный счет!',*/}
                            {/*        },*/}
                            {/*    ]}*/}
                            {/*>*/}
                            {/*    <Input*/}
                            {/*        name="companyLegalAddress.bankInformation.bankName"*/}
                            {/*        size="middle"*/}
                            {/*        disabled={!this.state.isEditing}*/}
                            {/*        value={this.state.companyLegalAddress.bankInformation.bankName.value}>*/}
                            {/*    </Input>*/}
                            {/*</Form.Item>*/}
                            {/*<Form.Item*/}
                            {/*    className={s.formItem}*/}
                            {/*    label={'идентификациооный номер банка в системе'}*/}
                            {/*    validateStatus={this.state.companyLegalAddress.bankInformation.bankCode.validateStatus}*/}
                            {/*    onChange={(event) => this.handleInputChange(event, validateEmail)}*/}
                            {/*    help={this.state.companyLegalAddress.bankInformation.bankCode.errorMsg}*/}
                            {/*    rules={[*/}
                            {/*        {*/}
                            {/*            required: true,*/}
                            {/*            message: 'Пожалуйста, введите идентификациооный номер банка в системе!',*/}
                            {/*        },*/}
                            {/*    ]}*/}
                            {/*>*/}
                            {/*    <Input*/}
                            {/*        name="companyLegalAddress.bankInformation.bankCode"*/}
                            {/*        size="middle"*/}
                            {/*        disabled={!this.state.isEditing}*/}
                            {/*        value={this.state.companyLegalAddress.bankInformation.bankCode.value}>*/}
                            {/*    </Input>*/}
                            {/*</Form.Item>*/}
                            {/*<Form.Item*/}
                            {/*    className={s.formItem}*/}
                            {/*    label={'идентификациооный номер банка в системе'}*/}
                            {/*    validateStatus={this.state.companyLegalAddress.bankInformation.postalCode.validateStatus}*/}
                            {/*    onChange={(event) => this.handleInputChange(event, validateEmail)}*/}
                            {/*    help={this.state.companyLegalAddress.bankInformation.postalCode.errorMsg}*/}
                            {/*    rules={[*/}
                            {/*        {*/}
                            {/*            required: true,*/}
                            {/*            message: 'Пожалуйста, введите идентификациооный номер банка в системе!',*/}
                            {/*        },*/}
                            {/*    ]}*/}
                            {/*>*/}
                            {/*    <Input*/}
                            {/*        name="companyLegalAddress.bankInformation.postalCode"*/}
                            {/*        size="middle"*/}
                            {/*        disabled={!this.state.isEditing}*/}
                            {/*        value={this.state.companyLegalAddress.bankInformation.postalCode.value}>*/}
                            {/*    </Input>*/}
                            {/*</Form.Item>*/}
                            {/*<Form.Item*/}
                            {/*    className={s.formItem}*/}
                            {/*    label={'введите адрес банка'}*/}
                            {/*    validateStatus={this.state.companyLegalAddress.bankInformation.address.validateStatus}*/}
                            {/*    onChange={(event) => this.handleInputChange(event, validateEmail)}*/}
                            {/*    help={this.state.companyLegalAddress.bankInformation.address.errorMsg}*/}
                            {/*    rules={[*/}
                            {/*        {*/}
                            {/*            required: true,*/}
                            {/*            message: 'Пожалуйста, введите адрес банка!',*/}
                            {/*        },*/}
                            {/*    ]}*/}
                            {/*>*/}
                            {/*    <Input*/}
                            {/*        name="companyLegalAddress.bankInformation.address"*/}
                            {/*        size="middle"*/}
                            {/*        disabled={!this.state.isEditing}*/}
                            {/*        value={this.state.companyLegalAddress.bankInformation.address.value}>*/}
                            {/*    </Input>*/}
                            {/*</Form.Item>*/}


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


                            <div className="row ">
                                <div className="col">

                                    <Form.Item className={s.formItem} wrapperCol={{...layout.wrapperCol, offset: 8}}>

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
                    </div>


                </div>

            </div>


        )
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
            validateStatus: SUCCESS,
            errorMsg: null,
        }
    }

    validateLicenceNumber = (number) => {

        console.log('licence number for validation: ' + number)

        if (number === undefined || number.length > COMPANY_LICENCE_NUM_MAX_LENGTH) {
            return {
                validateStatus: ERROR,
                errorMsg: localizedStrings.alertCompanyLicenceNumberTooLong
            }
        }

        return {
            validateStatus: SUCCESS,
            errorMsg: null,
        }
    }

    validateLegalAddress = (legalAddress) => {

        console.log('legalAddress for validation: ' + legalAddress)

        if (legalAddress === undefined) {
            return {
                validateStatus: ERROR,
                errorMsg: localizedStrings.alertCompanyLicenceNumberTooLong
            }
        }

        return {
            validateStatus: SUCCESS,
            errorMsg: null,
        }
    }


    validatePayerAccountNumber = (payerAccountNumber) => {
        return {
            validateStatus: SUCCESS,
            errorMsg: null,
        }
    }

}

export default CompanyForm
