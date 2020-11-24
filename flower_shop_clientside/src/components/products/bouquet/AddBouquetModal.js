import React, {Component} from 'react'
import {withRouter} from 'react-router-dom'
import Modal from "antd/es/modal";
import {saveBouquetRequest, saveFlowerRequest} from "../../util/utilsAPI";
import {localizedStrings} from "../../util/localization";
import {Button, notification} from "antd";
import BouquetForm from "./BouquetForm";


class AddBouquetModal extends Component {

    state = {
        bouquet: {
            id: "",
            dateOfLastUpdate: "",
            bouquetType: "",
            flowerColors: [],
            flowerLengthCosts: [],
            flowerSorts: [],
            country: "",
            title: "",
            description: "",
            availableAmountOnStock: ""
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


    handleSubmitButton = (bouquetRequest) => {

        console.log('bouquet request: ' + bouquetRequest)

        saveBouquetRequest(bouquetRequest)
            .then(() => {
                notification.success({
                    message: localizedStrings.alertAppName,
                    description: 'Букет сохранен!',
                })
                this.handleCancel()

            }).catch(error => {
            notification.error({
                message: localizedStrings.alertAppName,
                description: 'Чет пошло не так. сорян'
            })
        })
    }


    render() {

        return (
            <div>
                <Button type="primary"
                        style={{background: "black", color: "white"}}
                        onClick={this.showModal}
                >
                    Добавить букет
                </Button>

                <Modal
                    title="Добавить букет"
                    visible={this.state.visible}
                    okButtonProps={{style: {display: 'none'}}}
                    onCancel={this.handleCancel}
                    centered
                    width={1200}
                >

                    <BouquetForm
                        bouquet={this.state.bouquet}
                        action={'Добавить'}
                        validateStatus={''}
                        shopId={this.props.shopId}
                        handleSubmitButton={this.handleSubmitButton}
                    />

                </Modal>
            </div>
        );
    }


}

export default withRouter(AddBouquetModal)
