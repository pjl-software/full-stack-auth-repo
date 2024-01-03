FROM node:20

# Do not run this file directly. Use the ./bin/docker-startup-scripts instead
# We leverage environment variables that must be passed in when calling build

ARG ANGULAR_WORKDIR
WORKDIR /$ANGULAR_WORKDIR

COPY ./package*.json ./

RUN npm install

RUN npm install -g @angular/cli

COPY . .

EXPOSE 4200

CMD ["ng", "serve", "--host", "0.0.0.0", "--ssl", "true", "--ssl-cert", "./ssl-assets/server.crt", "--ssl-key", "./ssl-assets/server.key"]
