import React, {Component} from 'react'

import s from "../../user/profile/Profile.module.css";
import {Button, Form, Select} from "antd";
import ImageLoader from "../../common/image/ImageLoader";
import {
    getCountriesRequest,
    getFlowerColorsRequest,
    getFlowerSortsRequest,
    getFlowerTypesRequest
} from "../../util/utilsAPI";
import {ERROR, SUCCESS} from "../../../constants";
import Input from "antd/es/input";
import validateId from "../product/ProductValidation";

const Option = Select.Option;

const layout = {
    labelCol: {
        span: 8,
    },
    wrapperCol: {
        span: 16,
    },
};


export default class FlowerForm extends Component {


    state = {


        flowerTypesValues: [],
        flowerSortsValues: [],
        flowerColorsValues: [],
        countiesValues: [],


        shopId: this.props.shopId,
        id: this.props.flower.id,

        dateOfLastUpdate: {
            value: this.props.flower.dateOfLastUpdate,
            validateStatus: this.props.validateStatus
        },

        flowerType: {
            id: this.props.flower.flowerType.id,
            value: this.props.flower.flowerType.flowerType,
            validateStatus: this.props.validateStatus
        },
        flowerColors: {
            values: this.props.flower.flowerColors,
            validateStatus: this.props.validateStatus
        },
        flowerLengthCosts: {
            value: this.props.flower.flowerLengthCosts,
            validateStatus: this.props.validateStatus
        },
        flowerSorts: {
            values: this.props.flower.flowerSorts,
            validateStatus: this.props.validateStatus
        },
        country: {
            id: this.props.flower.country.id,
            value: this.props.flower.country.countryNameRu,
            validateStatus: this.props.validateStatus
        },
        description: {
            value: this.props.flower.description,
            validateStatus: this.props.validateStatus
        },
        availableAmountOnStock: {
            value: this.props.flower.availableAmountOnStock,
            validateStatus: this.props.validateStatus
        },

        imageUrl: this.props.flower.image.imageUrl
    }

    componentDidMount() {
        this.resolveCountries()
        this.resolveFlowerSorts()
        this.resolveFlowerTypes()
        this.resolveFlowerColors()
    }

    resolveCountries() {
        const promise = getCountriesRequest()

        console.log('resolve countries')

        promise
            .then(response => {

                this.setState({
                    countiesValues: response
                });

            }).catch(() => {

        });
    }

    resolveFlowerTypes() {
        const promise = getFlowerTypesRequest()

        console.log('resolve getFlowerTypesRequest')

        promise
            .then(response => {

                this.setState({
                    flowerTypesValues: response
                });

            }).catch(() => {
        });
    }

    resolveFlowerSorts() {
        const promise = getFlowerSortsRequest()

        console.log('resolve getFlowerSortsRequest')

        promise
            .then(response => {

                this.setState({
                    flowerSortsValues: response
                });

            }).catch(() => {
        });
    }


    resolveFlowerColors() {
        const promise = getFlowerColorsRequest()

        console.log('resolve getFlowerColorsRequest')

        promise
            .then(response => {

                this.setState({
                    flowerColorsValues: response
                });

            }).catch(() => {
        });
    }


    isFormInvalid = () => {

        return !(
            this.state.country.validateStatus === SUCCESS
        )
    }

    handleSubmit = () => {

        console.log('this.state.shopId   ' + this.state.shopId)


        const flowerRequest = {

            "shop": {
                "id": this.state.shopId,
            },

            "id": this.state.id,
            "flowerType": {
                "id": this.state.flowerType.id
            },
            "flowerColors": this.state.flowerColors.values,
            "flowerLengthCosts": [
                {
                    "stemLength": 70.5,
                    "price": 25.3
                }
            ],
            "flowerSorts": this.state.flowerSorts.values,
            "country": {
                "id": this.state.country.id
            },
            "description": this.state.description.value,
            "availableAmountOnStock": this.state.availableAmountOnStock.value,
            "image": {
                "imageUrl": this.state.imageUrl
            }
        }

        console.log('flowerRequest request: ' + flowerRequest)

        this.props.handleSubmitButton(flowerRequest);
    }


    render() {

        const countriesOptions = this.state.countiesValues.map(
            element =>
                <Option key={element.id} value={element.countryNameRu}>
                    {element.countryNameRu}
                </Option>
        )

        const flowerColorsOptions = this.state.flowerColorsValues.map(
            element =>
                <Option key={element.id}
                        value={element.colorName}
                        label={element.colorName}
                >
                    {element.colorName}
                </Option>
        )

        const flowerTypesOptions = this.state.flowerTypesValues.map(
            element =>
                <Option key={element.id} value={element.flowerType}>
                    {element.flowerType}
                </Option>
        )

        const flowerSortsOptions = this.state.flowerSortsValues.map(
            element =>
                <Option key={element.id} value={element.sortNameRu}>
                    {element.sortNameRu}
                </Option>
        )


        return (

            <div>
                <div>

                    <div>
                        <Form {...layout}
                              onFinish={this.handleSubmit}
                              initialValues={{}}
                        >

                            <div className="col-sm-12">
                                <div className="row">
                                    <div className="col-sm-6">
                                        <ImageLoader
                                            imageUrl={this.state.imageUrl}
                                            handleImageUrlChange={this.handleImageUrlChange}
                                        />
                                    </div>

                                    <div className="col-sm-6">

                                        <Form.Item
                                            className={s.formItem}
                                            label={'Страна поставщик'}
                                            validateStatus={this.state.country.validateStatus}
                                            hasFeedback
                                            help={this.state.country.errorMsg}
                                            // name="country"
                                        >


                                            <Select
                                                name="country"
                                                value={this.state.country.value}
                                                showSearch
                                                style={{width: 200}}
                                                placeholder="Выберите страну"
                                                onChange={this.onChangeCountrySelect}
                                            >
                                                {countriesOptions}
                                            </Select>

                                        </Form.Item>


                                        <Form.Item
                                            className={s.formItem}
                                            label={'Тип цветка'}
                                            validateStatus={this.state.flowerType.validateStatus}
                                            hasFeedback
                                            help={this.state.flowerType.errorMsg}
                                            // name="flowerType"
                                        >

                                            <Select
                                                name="flowerType"
                                                value={this.state.flowerType.value}
                                                showSearch
                                                style={{width: 200}}
                                                placeholder="Выберите тип"
                                                onChange={this.onChangeFlowerTypeSelect}
                                            >
                                                {flowerTypesOptions}
                                            </Select>

                                        </Form.Item>


                                        <Form.Item
                                            className={s.formItem}
                                            label={'Цвета'}
                                            validateStatus={this.state.flowerColors.validateStatus}
                                            hasFeedback
                                            help={this.state.flowerColors.errorMsg}
                                            // name="flowerColors"
                                        >

                                            <Select
                                                mode="multiple"
                                                allowClear
                                                // value={this.state.flowerColors.values}
                                                // defaultValue={this.state.flowerColors.values}
                                                showSearch
                                                style={{width: 200}}
                                                placeholder="Выберите цвета"
                                                onChange={this.onChangeColorsSelect}
                                            >
                                                {flowerColorsOptions}
                                            </Select>

                                        </Form.Item>


                                        <Form.Item
                                            className={s.formItem}
                                            label={'Сорта'}
                                            validateStatus={this.state.flowerSorts.validateStatus}
                                            hasFeedback
                                            help={this.state.flowerSorts.errorMsg}
                                            // name="flowerSorts"
                                        >

                                            <Select
                                                mode="multiple"
                                                allowClear
                                                // value={this.state.flowerSorts.values}
                                                showSearch
                                                style={{width: 200}}
                                                placeholder="Выберите сорт"
                                                onChange={this.onChangeFlowerSortsSelect}
                                            >
                                                {flowerSortsOptions}
                                            </Select>

                                        </Form.Item>


                                        <Form.Item
                                            className={s.formItem}
                                            label={'Описание'}
                                            validateStatus={this.state.description.validateStatus}
                                            hasFeedback
                                            onChange={(event) => this.handleInputChange(event, this.validateDescription)}
                                            help={this.state.description.errorMsg}
                                            rules={[
                                                {
                                                    required: true,
                                                    message: 'Пожалуйста, введите описание!',
                                                },
                                            ]}
                                            // name="description"
                                        >
                                            <Input.TextArea
                                                name="description"
                                                value={this.state.description.value}
                                                placeholder={'описание'}
                                                style={{fontSize: '16px'}}
                                                autosize={{minRows: 3, maxRows: 6}}/>
                                        </Form.Item>


                                        <Form.Item
                                            className={s.formItem}
                                            label={'Колво на складе'}
                                            validateStatus={this.state.availableAmountOnStock.validateStatus}
                                            hasFeedback
                                            onChange={(event) => this.handleInputChange(event, this.validateAmountOnStock)}
                                            help={this.state.availableAmountOnStock.errorMsg}
                                            rules={[
                                                {
                                                    required: true,
                                                    message: 'Пожалуйста, введите описание!',
                                                },
                                            ]}
                                            // name="availableAmountOnStock"
                                        >
                                            <Input
                                                type={"number"}
                                                min={0}
                                                max={10_000}
                                                name="availableAmountOnStock"
                                                placeholder={'колво на складе'}
                                                style={{fontSize: '16px'}}
                                                value={this.state.availableAmountOnStock.value}
                                                autosize={{minRows: 3, maxRows: 6}}/>
                                        </Form.Item>

                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-8"></div>
                                    <div className="col-4">
                                        <Form.Item className={s.formItem}>
                                            <Button
                                                type="primary"
                                                htmlType="submit"
                                                size="large"
                                                className={s.button}
                                                disabled={this.isFormInvalid()}>
                                                {this.props.action}
                                            </Button>
                                        </Form.Item>
                                    </div>
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


    handleImageUrlChange = (imageUrl) => {
        this.setState({
            imageUrl: imageUrl
        })
    }


    onChangeCountrySelect = (input, option) => {
        this.setState({
            country: {
                id: option.props.key,
                value: option.props.value,
                ...validateId(option.props.key)
            }
        })
    }

    onChangeFlowerTypeSelect = (input, option) => {
        this.setState({
            flowerType: {
                id: option.props.key,
                value: option.props.value,
                ...validateId(option.props.key)
            }
        })
    }

    onChangeColorsSelect = (keys, value) => {
        console.log(value)
        value.map(item => {
            this.state.flowerColors.values.push({
                "id": item.key
            });
        })
    }


    onChangeFlowerSortsSelect = (keys, value) => {
        console.log(value)


        value.map(item => {
            this.state.flowerSorts.values.push({
                "id": item.key
            });
        })

    }


    validateDescription = (description) => {
        if (description === undefined || description.length > 520) {
            return {
                validateStatus: ERROR,
                errorMsg: 'Описание слишком длинное'
            }
        }

        return {
            validateStatus: SUCCESS,
            errorMsg: null
        }
    }

    validateAmountOnStock = (amount) => {
        if (amount === undefined || amount > 10_000) {
            return {
                validateStatus: ERROR,
                errorMsg: 'Количество слишком большое'
            }
        }

        return {
            validateStatus: SUCCESS,
            errorMsg: null
        }
    }


}

