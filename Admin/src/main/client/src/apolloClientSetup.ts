import { ApolloClient, InMemoryCache, createHttpLink } from '@apollo/client';
import { setContext } from '@apollo/client/link/context';
import {createUploadLink} from 'apollo-upload-client';

const httpLink = createUploadLink({
  uri: '/graphql'
});

const authLink = setContext((_, {headers}) => {
  const token = localStorage.getItem('token');
  return {
      headers: {...headers, Authorization: token ? "Bearer "+token : ""}
  };
});

export default new ApolloClient({
    link: authLink.concat(httpLink),
    cache: new InMemoryCache()
});