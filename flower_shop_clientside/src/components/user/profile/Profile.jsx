import React, {Component} from 'react'
// import './Profile.module.css'
import {localizedStrings} from '../../util/localization'
// import s from "../login/Login.module.css";
import s from "../profile/Profile.module.css";
import {Button, Form, Input, notification} from "antd";

import ChangePasswordModal from "../modal/ChangePasswordModal";

import {
    ERROR,
    LOGIN_MAX_LENGTH,
    NAME_MAX_LENGTH,
    NAME_MIN_LENGTH,
    SUCCESS
} from "../../../constants";
import {checkLoginAvailabilityRequest, updateUserProfileRequest} from "../../util/utilsAPI";


class Profile extends Component {

    state = {
        color: '',
        currentUser: this.props.currentUser,

        name: {
            value: this.props.currentUser.name
        },
        email: {
            value: this.props.currentUser.email
        },
        phoneNumber: {
            value: this.props.currentUser.phoneNumber
        },

        imageUrl: this.props.currentUser.imageUrl,

        isEditing: false

    }


    submitColor = () => {
        console.log(this.state.color)
    }

    changeColor = (event) => {
        console.log('Change on pick by Input' + this.state.color)
        this.setState({
            color: event.target.value
        })
    }

    editProfile = () => {
        if (this.state.isEditing === false) {
            console.log('Start Edit profile')
            this.setState({
                isEditing: true
            })

        } else {
            console.log('Save Edited profile')
            this.setState({
                isEditing: false
            })

            const updateUserRequest = {
                id: this.state.currentUser.id,
                name: this.state.name.value,
                email: this.state.email.value,
            }

            console.log(updateUserRequest)

            updateUserProfileRequest(updateUserRequest)
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
        // const { currentUser } = this.props

        return (


            <div className={s.profileContainer}>
                <div className={s.content}>

                    <div>
                        <Form className={s.form}>
                            <Form.Item
                                className={s.formItem}
                                label={localizedStrings.yourName}
                                validateStatus={this.state.name.validateStatus}
                                hasFeedback
                                onChange={(event) => this.handleInputChange(event, this.validateName)}
                                help={this.state.name.errorMsg}
                            >
                                <Input
                                    name="name"
                                    type=""
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.name.value}>
                                </Input>
                            </Form.Item>

                            <Form.Item
                                className={s.formItem}
                                label={localizedStrings.yourLogin}
                                validateStatus={this.state.email.validateStatus}
                                onBlur={this.validateLoginAvailability}
                                onChange={(event) => this.handleInputChange(event, this.validateLogin)}
                                help={this.state.email.errorMsg}>
                                <Input
                                    name="email"
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.email.value}>
                                </Input>
                            </Form.Item>

                            <Form.Item
                                className={s.formItem}
                                label={localizedStrings.yourPhoneNumber}
                                validateStatus={this.state.phoneNumber.validateStatus}
                                onChange={(event) => this.handleInputChange(event, this.validatePhoneNumber)}
                                help={this.state.phoneNumber.errorMsg}>
                                <Input
                                    name="phoneNumber"
                                    type="phoneNumber"
                                    size="middle"
                                    disabled={!this.state.isEditing}
                                    value={this.state.phoneNumber.value}>
                                </Input>
                            </Form.Item>

                            <Button onClick={this.editProfile} className={s.button}>
                                {localizedStrings.edit}
                            </Button>

                            <ChangePasswordModal
                                currentUserId = {this.state.currentUser.id} />


                        </Form>
                    </div>

                    <div>
                        <div className="container">

                            <div className="profile-info">

                                <div className="profile-avatar">
                                    {
                                        this.state.imageUrl ? (
                                            <img src={this.state.imageUrl}
                                                 alt={this.state.name.value}/>
                                        ) : (
                                            <div>
                                                <div className="text-avatar">
                                                    <span>{this.state.name.value && this.state.name.value[0]}</span>
                                                </div>
                                                <div className="color-pick">
                                                    <form>
                                                        <label>
                                                            {localizedStrings.helpForChooseProfileColor}
                                                            <input type="color" onChange={this.changeColor}/>
                                                        </label>
                                                        <button
                                                            onClick={this.submitColor}>{localizedStrings.chooseColor}</button>
                                                    </form>

                                                </div>
                                            </div>

                                        )
                                    }
                                </div>

                            </div>

                        </div>
                    </div>

                </div>
            </div>



            // </div>
            // </div>
            // </div>
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
                errorMsg: localizedStrings.alertBadNameEmpty
            }
        } else if (name.length < NAME_MIN_LENGTH) {
            return {
                validateStatus: ERROR,
                errorMsg: localizedStrings.alertBadNameTooShort
            }
        } else if (name.length > NAME_MAX_LENGTH) {
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


    validateLogin = (email) => {

        if (email === this.props.currentUser.email) {
            return {
                validateStatus: SUCCESS,
                errorMsg: null
            }
        }

        if (!email) {
            return {
                validateStatus: ERROR,
                errorMsg: localizedStrings.alertLoginEmpty
            }
        }

        if (email.length > LOGIN_MAX_LENGTH) {
            return {
                validateStatus: ERROR,
                errorMsg: localizedStrings.alertBadLoginTooLong
            }
        }

        return {
            validateStatus: null,
            errorMsg: null
        }
    }

    validateLoginAvailability = () => {
        const emailValue = this.state.email.value
        const emailValidation = this.validateLogin(emailValue)

        if (emailValidation.validateStatus === ERROR) {
            this.setState({
                email: {
                    value: emailValue,
                    ...emailValidation
                }
            })
            return
        }

        this.setState({
            email: {
                value: emailValue,
                validateStatus: 'validating',
                errorMsg: null
            }
        })

        checkLoginAvailabilityRequest(emailValue)
            .then(response => {
                if (response.available) {
                    this.setState({
                        email: {
                            value: emailValue,
                            validateStatus: SUCCESS,
                            errorMsg: null
                        }
                    })
                } else {
                    this.setState({
                        email: {
                            value: emailValue,
                            validateStatus: ERROR,
                            errorMsg: localizedStrings.alertLoginAlreadyRegistred
                        }
                    })
                }
            }).catch(() => {
            this.setState({
                email: {
                    value: emailValue,
                    validateStatus: SUCCESS,
                    errorMsg: null
                }
            })
        })

        this.setState({
            email: {
                value: emailValue,
                validateStatus: SUCCESS,
                errorMsg: null
            }
        })
    }




}

export default Profile
