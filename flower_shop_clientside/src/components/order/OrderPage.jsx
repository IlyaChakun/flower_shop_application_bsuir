import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'
import {Button, Collapse, Form, Input, List, notification} from 'antd'
import OrderProduct from "./OrderProduct";
import s from "../company/Company.module.css";
import {localizedStrings} from "../util/localization";
import {validateEmail, validateText} from "../common/validation/ValidationFunctions";
import {
    getCurrentCompanyRequest,
    saveCompanyRequest,
    saveOrderRequest,
    updateCompanyInfoRequest
} from "../util/utilsAPI";
import {SUCCESS} from "../../constants";


const {TextArea} = Input;

const layout = {
    labelCol: {
        span: 9,
    },
    wrapperCol: {
        span: 15,
    },
}

class OrderPage extends Component {

    state = {
        orderProducts: this.props.order,

        comment: {
            value: ""
        },
        address: {
            value: ""
        },
        floorNumber: {
            value: ""
        },
        entranceNumber: {
            value: ""
        }
    }


    componentDidMount() {
        this.resolveCompany()
    }

    resolveCompany() {
        const company = getCurrentCompanyRequest()
        company
            .then(response => {

                console.log(response.name)


                this.setState({
                    name: {
                        value: response.name,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    description: {
                        value: response.description,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    licenceNumber: {
                        value: response.licenceNumber,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },

                    firstPhoneNumber: {
                        value: response.contacts.firstPhoneNumber,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    secondPhoneNumber: {
                        value: response.contacts.secondPhoneNumber,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    email: {
                        value: response.contacts.email,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    city: {
                        value: response.contacts.city,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    address: {
                        value: response.contacts.address,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    payerAccountNumber: {
                        value: response.companyLegalAddress.payerAccountNumber,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    checkingAccount: {
                        value: response.companyLegalAddress.checkingAccount,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    bankName: {
                        value: response.companyLegalAddress.bankInformation.bankName,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    bankCode: {
                        value: response.companyLegalAddress.bankInformation.bankCode,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    postalCode: {
                        value: response.companyLegalAddress.bankInformation.postalCode,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    bankAddress: {
                        value: response.companyLegalAddress.bankInformation.address,
                        validateStatus: SUCCESS,
                        errorMsg: null
                    },
                    shops: {...response.shops},

                    isEditing: false,
                    isExist: true
                })
            }).catch(error => {

            this.setState({
                isEditing: false
            })
            notification.error({
                message: localizedStrings.alertAppName,
                description: 'У вас еще нет компании! А ну ка создайте!'
            })
        })
    }

    handleInputChange = (event, validationFun) => {

        console.log('event ' + event)
        console.log('event ' + event.target.name)

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
        }
    }


    handleSubmit = () => {
        const orderRequest = {
            "comment": this.state.comment.value,
            "address": this.state.address.value,
            "floorNumber": this.state.floorNumber.value,
            "entranceNumber": this.state.entranceNumber.value,
        }

        console.log('order request: ' + orderRequest)


        saveOrderRequest(orderRequest)
            .then(() => {
                notification.success({
                    message: localizedStrings.alertAppName,
                    description: 'Заказ оформлен!',
                })

            }).catch(error => {
            notification.error({
                message: localizedStrings.alertAppName,
                description: 'Чет пошло не так. сорян'
            })
        })

    }


    isFormInvalid = () => {
        return !(
            this.state.comment.validateStatus === SUCCESS &&
            this.state.address.validateStatus === SUCCESS &&
            this.state.floorNumber.validateStatus === SUCCESS &&
            this.state.entranceNumber.validateStatus === SUCCESS
        )
    }


    render() {

        const orderProducts = this.state.order.orderProducts
            .map(orderProduct => (
                    <OrderProduct
                        key={orderProduct.id}
                        orderProduct={orderProduct}
                    />
                )
            )
        return (
            <div>
                <p> Заказ </p>

                <p>Список товаров:</p>

                <List
                    grid={{
                        gutter: 70,
                        column: 3,
                    }}

                    pagination={{

                        // loading: this.state.isLoading,
                        showSizeChanger: true,

                        defaultCurrent: Number(this.state.page),
                        defaultPageSize: Number(this.state.size),

                        pageSizeOptions: ["6", "9", "12"],
                        position: "bottom",

                        total: this.state.totalElements,

                        showQuickJumper: true,
                        onShowSizeChange: this.onSizeChangeHandler,
                        onChange: this.onPageChangeHandler,

                        loadMore: this.loadMore
                    }}

                    dataSource={orderProducts}

                    renderItem={item => (
                        <List.Item>
                            {item}
                        </List.Item>
                    )}
                />


                <div>
                    <Form {...layout}
                          onFinish={this.handleSubmit} className={s.form}
                    >

                        <Form.Item
                            className={s.formItem}
                            label={'Комментарий к заказу'}
                            validateStatus={this.state.comment.validateStatus}
                            hasFeedback
                            onChange={(event) => this.handleInputChange(event, validateText)}
                            help={this.state.comment.errorMsg}
                        >
                            <TextArea
                                rows={5}
                                name="comment"
                                size="middle"
                                disabled={!this.state.isEditing}
                                value={this.state.comment.value}>
                            </TextArea>
                        </Form.Item>

                        <Form.Item
                            className={s.formItem}
                            label={'Адрес'}
                            validateStatus={this.state.address.validateStatus}
                            hasFeedback
                            onChange={(event) => this.handleInputChange(event, validateEmail)}
                            help={this.state.address.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите адрес!',
                                },
                            ]}
                        >
                            <Input
                                name="address"
                                placeholder={'Адрес'}
                                style={{fontSize: '16px'}}
                                autosize={{minRows: 3, maxRows: 6}}/>
                        </Form.Item>


                        <Form.Item
                            className={s.formItem}
                            label={'Этаж'}
                            validateStatus={this.state.floorNumber.validateStatus}
                            hasFeedback
                            onChange={(event) => this.handleInputChange(event, validateEmail)}
                            help={this.state.floorNumber.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите этаж!',
                                },
                            ]}
                        >
                            <Input
                                name="floorNumber"
                                placeholder={'Этаж'}
                                style={{fontSize: '16px'}}
                                autosize={{minRows: 3, maxRows: 6}}/>
                        </Form.Item>

                        <Form.Item
                            className={s.formItem}
                            label={'Подъезд'}
                            validateStatus={this.state.entranceNumber.validateStatus}
                            hasFeedback
                            onChange={(event) => this.handleInputChange(event, validateEmail)}
                            help={this.state.entranceNumber.errorMsg}
                            rules={[
                                {
                                    required: true,
                                    message: 'Пожалуйста, введите подъезд!',
                                },
                            ]}
                        >
                            <Input
                                name="entranceNumber"
                                placeholder={'Подъезд'}
                                style={{fontSize: '16px'}}
                                autosize={{minRows: 3, maxRows: 6}}/>
                        </Form.Item>

                        <Form.Item className={s.formItem}>
                            <Button
                                type="primary"
                                htmlType="submit"
                                size="large"
                                className={s.button}
                                disabled={this.isFormInvalid()}>
                                Заказать
                            </Button>
                        </Form.Item>
                    </Form>
                </div>


            </div>


        )
    }


    onSizeChangeHandler = (page, size) => {

        this.setState({
            page: page,
            size: size
        });
        this.loadList(page, size);
    };

    onPageChangeHandler = (pageNumber) => {

        console.log('onPageChangeHandler')
        console.log('pageNumber', pageNumber)
        console.log('totalElements', this.state.totalElements)
        console.log('totalPages', this.state.totalPages)

        this.setState({
            page: pageNumber
        });


        this.loadList(pageNumber, this.state.size);
    };

    loadMore = () => {

        console.log('LOAD MORE WORKS')

        this.loadList(this.state.page + 1, this.state.size);
    }

}

export default withRouter(OrderPage)

