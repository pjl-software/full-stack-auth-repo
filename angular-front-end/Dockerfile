FROM node:20

WORKDIR /angular-app

COPY ./package*.json ./

RUN npm install

RUN npm install -g @angular/cli

COPY . .

EXPOSE 4200

CMD ["ng", "serve", "--host", "0.0.0.0", "--ssl", "true", "--ssl-cert", "./ssl-assets/server.crt", "--ssl-key", "./ssl-assets/server.key"]
