import React, { useState } from 'react';
import styles from './Hello.module.css';
import { useQuery, gql, useLazyQuery, useMutation } from '@apollo/client';

interface HelloVars {
    count: number;
}

interface HelloResult {
    hello: string
}

const GET_HELLO = gql`
  query GetHellos($count: Int!) {
    hello(count:$count)
  }
`;


export function Hello() {
    const { loading, data } = useQuery<HelloResult, HelloVars>(
        GET_HELLO,
        { variables: { count: 5 } }
    );


    const loadingPlaceholder = "Loading ...";
    return (
        <div>
            <div className={styles.row}>
                <span className={styles.value}>Result from GraphQL:
                        {loading ? loadingPlaceholder : (data && data.hello)}
                </span>
                <button
                    className={styles.button}
                    onClick={() => console.log('hello button')}>
                    Start campaign
                </button>
            </div>
        </div >
    );
}