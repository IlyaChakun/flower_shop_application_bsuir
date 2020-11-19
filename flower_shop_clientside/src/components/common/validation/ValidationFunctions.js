import { EMAIL_MAX_LENGTH, ERROR, NAME_MAX_LENGTH, NAME_MIN_LENGTH, SUCCESS } from '../../../constants'
import { localizedStrings } from '../../util/localization'

export function validateUserName (name) {
  if (name.length < NAME_MIN_LENGTH) {
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
      errorMsg: null
    }
  }
}

export function validateEmail (email) {
  if (!email) {
    return {
      validateStatus: ERROR,
      errorMsg: localizedStrings.alertLoginEmpty
    }
  }

  if (email.length > EMAIL_MAX_LENGTH) {
    return {
      validateStatus: ERROR,
      errorMsg: localizedStrings.alertBadLoginTooLong
    }
  }

  return {
    validateStatus: SUCCESS,
    errorMsg: null
  }
}

export function validatePhoneNumber (phoneNumber) {
  if (!phoneNumber) {
    return {
      validateStatus: ERROR,
      errorMsg: 'Нужно ввести номер телефона'
    }
  }

  if (phoneNumber.length > 12) {
    return {
      validateStatus: ERROR,
      errorMsg: 'Телефон слишком длинный'
    }
  }

  return {
    validateStatus: SUCCESS,
    errorMsg: null
  }
}

export function validateTextReviewText (text) {
  if (!text) {
    return {
      validateStatus: ERROR,
      errorMsg: 'Нужно ввести текст'
    }
  }

  if (text.length > 512) {
    return {
      validateStatus: ERROR,
      errorMsg: 'Текст слишком длинный'
    }
  }

  return {
    validateStatus: SUCCESS,
    errorMsg: null
  }
}
