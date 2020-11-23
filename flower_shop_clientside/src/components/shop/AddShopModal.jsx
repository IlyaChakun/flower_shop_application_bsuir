import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'

import {Button, Form, Input, Modal, notification} from "antd";
import s from "../company/Company.module.css";
import {localizedStrings} from "../util/localization";
import {
    validateAddress,
    validateCity, validateEmail,
    validatePhoneNumber
} from "../common/validation/ValidationFunctions";
import {saveShopRequest} from "../util/utilsAPI";
import {ERROR, SUCCESS} from "../../constants";


const layout = {
    labelCol: {
        span: 9,
    },
    wrapperCol: {
        span: 15,
    },
}

class AddShopModal extends Component {
    state = {
        firstPhoneNumber: {value: ""},
        secondPhoneNumber: {value: ""},
        email: {value: ""},
        city: {value: ""},
        address: {value: ""},
        workingHours: {value: ""},
        visible: false,
    }


    showModal = () => {
        this.setState({
            visible: true,
        });
    };

    handleOk = e => {
        console.log(e);
        this.setState({
            visible: false,
        });
    };

    handleCancel = e => {
        console.log(e);
        this.setState({
            visible: false,
        });
    };


    handleSubmit = () => {
        const shopRequest = {
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


    render() {
        return (
            <div className='mt-3 mb-5 float-right'>
                <Button type="primary" onClick={this.showModal}>
                    Добавить магазин
                </Button>

                <Modal
                    title="Оставить отзыв"
                    visible={this.state.visible}
                    onOk={this.handleOk}
                    onCancel={this.handleCancel}
                >

                    <Form {...layout}
                          onFinish={this.handleSubmit} className={s.form}>
                        <div className="col-sm-12">
                            <div className="row">
                                <div className="col-sm-6">здесь место для добавления фотки</div>

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
                        </div>
                    </Form>
                </Modal>
            </div>
        );
    }


    isFormInvalid = () => {
        return !(this.state.firstPhoneNumber.validateStatus === SUCCESS &&
            this.state.secondPhoneNumber.validateStatus === SUCCESS &&
            this.state.email.validateStatus === SUCCESS &&
            this.state.city.validateStatus === SUCCESS &&
            this.state.address.validateStatus === SUCCESS &&
            this.state.workingHours.validateStatus === SUCCESS)
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

export default withRouter(AddShopModal)
