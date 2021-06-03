import React, { useState } from 'react';
import styles from './CampaignDetails.module.css';
import { useQuery, gql } from '@apollo/client';

interface GetCampaignVars {
    id: string;
}

interface GetCampaignResult {
    id: string,
    name: string,
    startDate: string,
    endDate: string
}

const GET_CAMPAIGN = gql`
  query GetCampaign($id: Id!) {
    getCampaign(id:$id) {
        id
        name
        startDate
        endDate
    }
  }
`;


export function CampaignDetails() {
    const { loading, data } = useQuery<GetCampaignResult, GetCampaignVars>(
        GET_CAMPAIGN,
        { variables: { id: "185f4008-f9b6-c066-f345-d7c9e8c2b7bb" } }
    );


    const loadingPlaceholder = "Loading ...";
    return (
        <div>
            <div className={styles.row}>
                <span className={styles.value}>Result from GraphQL:
                        {loading ? loadingPlaceholder : (data && data.name)}
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