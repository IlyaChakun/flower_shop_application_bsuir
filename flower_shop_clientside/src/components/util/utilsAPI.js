import {
    BASE_URL,
    VALID_TOKEN_TYPE_VALUE,
    ACCESS_TOKEN_HEADER_KEY,
    REFRESH_TOKEN_HEADER_KEY,
    GRANT_TYPE_HEADER_KEY,
    TOKEN_TYPE_HEADER_KEY,

    ACCESS_TOKEN,
    REFRESH_TOKEN
} from '../../constants'

/**
 * Base method that provides fetch logic to server.
 * Also exactly here all security things will be sat to request headers.
 *
 * @param options
 * @returns {Promise<Response>}
 */
const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json'
    });

    if (localStorage.getItem(ACCESS_TOKEN)) {
        // headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))

        console.log('TOKEN PRESENT!')
        console.log(localStorage.getItem(ACCESS_TOKEN))
        console.log(localStorage.getItem(REFRESH_TOKEN))

        headers.append(TOKEN_TYPE_HEADER_KEY, VALID_TOKEN_TYPE_VALUE)
        headers.append(ACCESS_TOKEN_HEADER_KEY, localStorage.getItem(ACCESS_TOKEN))
        headers.append(REFRESH_TOKEN_HEADER_KEY, localStorage.getItem(REFRESH_TOKEN))
        headers.append(GRANT_TYPE_HEADER_KEY, 'action')// TODO придумать как сюда передавать grantType вижу как паарметр рядом  с options
    } else {
        headers.append(GRANT_TYPE_HEADER_KEY, 'anon_action')
    }

    const defaults = {headers: headers}
    options = Object.assign({}, defaults, options)

    return fetch(options.url, options)
        .then(response => {

            console.log('request error')
            console.log('error code= ' + response.status)

            if (response.status === 401) {
                console.log('remove all tokens')
                //сначала пробуем сделать обнолвение через refresh и если опять ошибка то удаляем токены из хранилища

                localStorage.removeItem(ACCESS_TOKEN)
                localStorage.removeItem(REFRESH_TOKEN)

                return response.json()
            }

            if (response.status !== 204) { //удаление
                return response.json()
            }

            if (!response.ok) {//если совсем пиздец
                throw response
            }


        }).then(json => {
            return json
        })
}


export function loginRequest(loginRequest) {
    return request({
        url: BASE_URL + 'auth/user/login',
        method: 'POST',
        body: JSON.stringify(loginRequest)
    })
}

export function signUpRequest(signupRequest) {
    return request({
        url: BASE_URL + 'auth/user/client/sign-up',
        method: 'POST',
        body: JSON.stringify(signupRequest)
    })
}

export function getCurrentUserRequest() {

    if (!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject('No access token set.')
    }

    return request({
        url: BASE_URL + 'users/me',
        method: 'GET'
    })

}

export function checkLoginAvailabilityRequest(login) {
    return request({
        url: BASE_URL + 'auth/user/check-email-availability?login=' + login,
        method: 'GET'
    })
}