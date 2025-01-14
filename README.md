# README

## Steps to Build and Run the Application Locally Using Docker

### Prerequisites
1. Install Docker and Docker Compose.
2. Clone this repository:
   ```bash
   git clone git@github.com:nickmwangemi/kyosk.git
   cd kyosk
   ```

### Using Docker Compose (Recommended)
1. Start the application and MongoDB:
   ```bash
   docker compose up -d
   ```

2. Check if services are running:
   ```bash
   docker compose ps
   ```

3. View logs:
   ```bash
   # All services
   docker compose logs

   # Specific service
   docker compose logs bookstore-app
   docker compose logs bookstore-mongodb
   ```

4. Stop the services:
   ```bash
   docker compose down

   # To also remove volumes
   docker compose down -v
   ```

5. Access the application at http://localhost:8080


### Docker Commands for Troubleshooting
1. List running containers:
   ```bash
   docker ps
   ```

2. Check container logs:
   ```bash
   docker logs [container_id]
   ```

3. Access container shell:
   ```bash
   docker exec -it [container_id] bash
   ```

4. Check MongoDB connection:
   ```bash
   docker compose exec mongodb mongosh --eval "db.adminCommand('ping')"
   ```

5. Check application health:
   ```bash
   curl http://localhost:8080/actuator/health
   ```

---

## Steps to Deploy the Application on Minikube

### Prerequisites
1. Install [Minikube](https://minikube.sigs.k8s.io/docs/start/).
2. Install [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/).
3. Ensure Docker is installed and running.

### Required Minikube Setup Commands
1. Start Minikube:
   ```bash
   minikube start
   ```
2. Point your Docker environment to Minikube’s Docker daemon:
   ```bash
   eval $(minikube docker-env)
   ```
3. Re-eval your docker-env to ensure your environment variables have updated ports:
   ```bash
   minikube -p minikube docker-env
   ```

4. Point your shell to minikube's docker-daemon:
   ```bash
   eval $(minikube -p minikube docker-env)
   ```

5. Build the Docker image inside Minikube:
   ```bash
   docker build -t kyosk-app:latest .
   ```

### Deploy the Application
1. Apply the Kubernetes manifests:
   ```bash
   kubectl apply -f k8s/
   ```
2. Verify the deployment:
   ```bash
   # Check deployment status
   kubectl get deployments
   kubectl get pods
   
   # Check service status
   kubectl get services
   ```


### Access the Application
1. Run:
   ```bash
   minikube service spring-boot-service
   ```
   
![img.png](img.png)


---

## Explanation of the CI/CD Pipeline in GitHub Actions

The GitHub Actions workflow automates the process of building, testing, and deploying the application:

1. **Triggers**:
    - The pipeline runs on pushes to the `main` branch and on pull requests targeting `main`.

2. **Steps**:
    - **Checkout the Code**: The repository is cloned into the runner environment.
    - **Log in to Docker Registry**: The workflow uses credentials stored as GitHub Secrets to log in to Docker.
    - **Build Docker Image**: The application is built into a Docker image.
    - **Push Docker Image**: The built image is pushed to Docker Hub.

3. **Secrets Required**:
    - `DOCKER_USERNAME`: Your Docker Hub username.
    - `DOCKER_PASSWORD`: Your Docker Hub access token (or password).

---

## Decisions, Assumptions, and Challenges Faced

### Decisions
- Opted for Docker Hub as the default image registry (can be switched to GitHub Container Registry).
- Chose `GitHub Actions` for CI/CD due to its seamless integration with the repository.

### Assumptions
- The user has Docker, Minikube, and kubectl installed and configured correctly.
- The application’s container listens on port `8080`.

### Challenges
- Handling cross-environment compatibility for Minikube’s Docker daemon.
- Managing Kubernetes manifests to balance simplicity and extensibility for scaling.

