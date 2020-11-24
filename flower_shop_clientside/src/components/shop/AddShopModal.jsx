import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'

import {Button, Form, Input, Modal, notification, Upload} from "antd";
import {localizedStrings} from "../util/localization";
import {saveShopRequest} from "../util/utilsAPI";
import ShopForm from "./ShopForm";
import {SUCCESS} from "../../constants";


class AddShopModal extends Component {
    state = {
        shop: {
            contacts: {
                firstPhoneNumber: "",
                secondPhoneNumber: "",
                email: "",
                city: "",
                address: "",
            },
            workingHours: "",
        }
    }


    showModal = () => {
        this.setState({
            visible: true,
        });
    };

    handleCancel = e => {
        console.log(e);
        this.setState({
            visible: false,
        });
    };


    handleSubmitButton = (shopRequest) => {

        console.log('shop request: ' + shopRequest)

        saveShopRequest(shopRequest)
            .then(() => {
                notification.success({
                    message: localizedStrings.alertAppName,
                    description: 'Магазин сохранен!',
                })
                this.handleCancel()
                this.props.history.push("/company/shops");
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
                    title="Добавить магазин"
                    visible={this.state.visible}
                    okButtonProps={{style: {display: 'none'}}}
                    onCancel={this.handleCancel}
                    centered
                    width={1200}
                >

                    <ShopForm
                        shop={this.state.shop}
                        action={'Добавить'}
                        validateStatus={''}
                        handleSubmitButton={this.handleSubmitButton}
                    />

                </Modal>
            </div>
        );
    }


}

export default withRouter(AddShopModal)
