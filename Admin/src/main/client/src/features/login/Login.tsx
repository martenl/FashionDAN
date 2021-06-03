import React, { useState, Component } from 'react';
import styles from './Login.module.css';
import { gql, useMutation } from '@apollo/client';

const LOGIN = gql`
  mutation login($email:String!, $password:String!) {
    login(email:$email, password:$password) {
      id
      token
    }
  }
`;
export function Login() {
        const [email, setEmail] = useState('');
        const [password, setPassword] = useState('');

        const [login, { data }] = useMutation(LOGIN);

        if(data) {
          localStorage.setItem('token', data.login.token);
        };

    return(
        <div>
                    <div className={styles.row}>
                        <input
                            className={styles.textbox}
                            aria-label="Email"
                            placeholder="Email"
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                        />
                        <input
                            type="password"
                            className={styles.textbox}
                            aria-label="Password"
                            placeholder="Password"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        />
                        <button
                            className={styles.asyncButton}
                            onClick={() => login({ variables: { email:email, password:password} })}
                        >
                            Login
                        </button>
                    </div>
                </div >
    );
}