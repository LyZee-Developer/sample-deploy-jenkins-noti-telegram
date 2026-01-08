FROM node:lts-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve the application with Nginx
FROM nginx:alpine as production-stage
# Create an app directory and copy built files
RUN mkdir /app
COPY --from=build /app/dist /usr/share/nginx/html
# Copy a custom nginx config if needed, otherwise use default
# COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]